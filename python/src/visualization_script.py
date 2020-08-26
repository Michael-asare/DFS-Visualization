import sys
import json
import datetime as dt
import matplotlib.pyplot as plt
import matplotlib.dates as mdates

class Plotter:
    def __init__(self):
        self.data_filename = sys.argv[1]
        self.y_axis = sys.argv[2]
        self.x_axis = sys.argv[3]
        self.name = sys.argv[4]
        self.y = []
        self.x = []

    def create_image(self):
        self.parse_json()
        if self.x_axis != "date":
            plt.scatter(x=self.x, y=self.y)
        else:
            plt.gca().xaxis.set_major_formatter(mdates.DateFormatter('%m/%d/%Y'))
            plt.gca().xaxis.set_major_locator(mdates.DayLocator())
            plt.plot(self.x, self.y)
            plt.gcf().autofmt_xdate()
        plt.xlabel(self.x_axis)
        plt.ylabel(self.y_axis)
        plt.title(self.name)
        plt.show()

    def parse_json(self):
        with open(self.data_filename) as json_file:
            data = json.load(json_file)
            for data_point in data:
                self.y.append(float(data_point[self.y_axis]))
                if self.x_axis != "date":
                    self.x.append(float(data_point[self.x_axis]))
                else:
                    date_value = self.remove_time(data_point[self.x_axis])
                    self.x.append(dt.datetime.strptime(date_value, '%Y-%m-%d').date())

    @staticmethod
    def remove_time(date):
        return date[:date.index(" ")]


p = Plotter()
p.create_image()
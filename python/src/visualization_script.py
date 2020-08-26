import sys
import json
import datetime as dt

class Plotter:
    def __init__(self):
        self.data_filename = sys.argv[1]
        self.y_axis = sys.argv[2]
        self.x_axis = sys.argv[3]
        self.y = []
        self.x = []

    def create_image(self):
        self.parse_json()

    def parse_json(self):
        with open(self.data_filename) as json_file:
            data = json.load(json_file)
            for data_point in data:
                self.y.append(float(data_point[self.y_axis]))
                if self.x_axis != "date":
                    self.x.append(float(data_point[self.x_axis]))
                else:
                    date_values = [self.remove_time(data_point[self.x_axis])]
                    x = [dt.datetime.strptime(d, '%Y-%m-%d').date() for d in date_values]

    @staticmethod
    def remove_time(date):
        return date[:date.index(" ")]


p = Plotter()
p.create_image()
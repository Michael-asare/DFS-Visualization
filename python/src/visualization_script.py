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
        """
        Creates and saves a matplotlib file

        :return:
        """

        # Parse json
        # Create image based off the axes
        # If the x axis is date, then create a specially formated axis, otherwise don't do anything special
        # Save figure after labeling graph

        self.parse_json()
        if self.x_axis != "date":
            plt.scatter(x=self.x, y=self.y)
        else:
            plt.gca().xaxis.set_major_formatter(mdates.DateFormatter('%m/%d/%Y'))
            plt.gca().xaxis.set_major_locator(mdates.DayLocator())
            plt.plot(self.x, self.y, '-.dm')
            plt.gcf().autofmt_xdate()
        plt.xlabel(self.x_axis)
        plt.ylabel(self.y_axis)
        plt.title(self.name)
        plt.savefig(self.data_filename[:len(self.data_filename) - 5] + "_" + self.y_axis + "vs" + self.x_axis + ".jpg")

    def parse_json(self):
        """
        Parses json of the data filename given through the command line

        :return:
        """

        # Opens data file
        # Loads the json
        # For each data point in the json file
        # Parse and add the data to the y axis
        # If the x axis is not date, then also parse and add data to the x axis
        # Else, Remove the external seconds data and change its format to be ready for the x axis
        # Then add it to the x axis
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
        """
        Removes the extraneous date data
        :param date: a date that has both the calendar date but also the seconds data
        :return: a date string without the data after the space
        """
        return date[:date.index(" ")]


p = Plotter()
p.create_image()
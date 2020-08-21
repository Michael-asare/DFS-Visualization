import sys
import pandas as pd
import lxml
import html5lib

URL = "http://rotoguru1.com/cgi-bin/hyday.pl?game=fd&mon=8&day=13&year=2020"
dfs = pd.read_html(URL)
print(len(dfs[5][2]))


# BASE_URL = "http://rotoguru1.com/cgi-bin/hyday.pl?mon=8&day=13&year=2020&game=fd"
# dfs = pd.read_html(BASE_URL)
# print(dfs[5][2])
# print(dfs[5][1])
# print(dfs[5][1][2] == "Lillard, Damian^")
# first index = table of interest
# second index = name (1), fanduel points (2), minutes (7)


class Collector:
    # The url that will be edited to determine the date to be analyzed
    BASE_URL = "http://rotoguru1.com/cgi-bin/hyday.pl?mon=MONTH&day=DAY&year=YEAR&game=fd"
    NAME_ROW = 1
    FANDUEL_POINTS_ROW = 2
    MINUTES_ROW = 7

    def __init__(self):
        self.first_name = sys.argv[0]
        self.last_name = sys.argv[1]
        self.start_date = sys.argv[2]
        self.end_date = sys.argv[3]

    def collect(self):
        for panda_date in pd.date_range(start=self.start_date, end=self.end_date):
            date = self.format_date(panda_date)
            year = date[0]
            month = date[1]
            day = date[2]
            # parsed_data = self.parse(self.format_url(year, month, day))
        return

    @staticmethod
    def format_date(panda_date):
        year = panda_date.year
        month = panda_date.month
        day = panda_date.day
        date = [year, month, day]
        return date

    @staticmethod
    def format_url(year, month, day):
        url = Collector.BASE_URL
        url = url.replace(year, "YEAR")
        url = url.replace(month, "MONTH")
        url = url.replace(day, "DAY")
        return url

    def parse(self, url):
        dfs = pd.read_html(url)
        if len(dfs) != 7:
            return
        for i in range(len(dfs[5][Collector.NAME_ROW])):
            return
        name = self.format_name()
        return

    @staticmethod
    def trim_name(name):
        if "^" in name:
            return name[:len(name) - 1]
        return name

    def format_name(self):
        last_name = self.last_name.lower().capitalize()
        first_name = self.first_name.lower().capitalize()
        name = last_name + ", " + first_name
        return name

    def write_to_csv(self):
        return


# c = Collector
# c.collect()

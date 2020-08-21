import sys
import pandas as pd
import lxml
import html5lib

# dfs = pd.read_html(BASE_URL)
# del dfs[5][2][0]
# print(dfs[5][2])


class Collector:
    # The url that will be edited to determine the date to be analyzed
    BASE_URL = "http://rotoguru1.com/cgi-bin/hyday.pl?mon=MONTH&day=DAY&year=YEAR&game=fd"

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
        return

    def format_name(self):
        return

    def write_to_csv(self):
        return


c = Collector
c.collect()

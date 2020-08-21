import sys
import pandas as pd
import lxml
import html5lib
import json


class Collector:
    # The url that will be edited to determine the date to be analyzed
    BASE_URL = "http://rotoguru1.com/cgi-bin/hyday.pl?mon=MONTH&day=DAY&year=YEAR&game=fd"
    DATE_BASE = "mon=MONTH&day=DAY&year=YEAR"
    # Table of Interest
    TOI = 5
    NAME_ROW = 1
    FANDUEL_POINTS_ROW = 2
    MINUTES_ROW = 7

    def __init__(self):
        self.first_name = sys.argv[1]
        self.last_name = sys.argv[2]
        self.start_date = sys.argv[3]
        self.end_date = sys.argv[4]
        self.player_data = []

    def collect(self):
        for panda_date in pd.date_range(start=self.start_date, end=self.end_date):
            date = self.format_date(panda_date)
            year = date[0]
            month = date[1]
            day = date[2]
            parsed_data = self.parse(self.format_url(year=year, month=month, day=day))
            if len(parsed_data) != 0:
                self.add_to_list(date=str(panda_date), data=parsed_data)
        self.write_to_json()

    @staticmethod
    def format_date(panda_date):
        year = panda_date.year
        month = panda_date.month
        day = panda_date.day
        date = [year, month, day]
        return date

    @staticmethod
    def format_url(year, month, day):
        str_month = "mon=" + str(month)
        str_day = "day=" + str(day)
        str_year = "year=" + str(year)
        query = str_month + "&" + str_day + "&" + str_year
        return Collector.BASE_URL.replace(Collector.DATE_BASE, query)

    def parse(self, url):
        try:
            dfs = pd.read_html(url)
            if len(dfs[Collector.TOI]) <= 1:
                return ""
            formatted_name = self.format_name()
            for i in range(len(dfs[Collector.TOI][Collector.NAME_ROW])):
                found_name = self.trim_name(dfs[Collector.TOI][Collector.NAME_ROW][i])
                found_minutes = dfs[Collector.TOI][Collector.MINUTES_ROW][i]
                if found_name == formatted_name:
                    if found_minutes != "0" and found_minutes != "NA" and found_minutes != "DNP":
                        found_fanduel_points = dfs[Collector.TOI][Collector.FANDUEL_POINTS_ROW][i]
                        return found_fanduel_points
            return ""
        except ValueError:
            return ""

    @staticmethod
    def trim_name(name):
        if "^" in name:
            end = len(name) - 1
            return name[:end]
        return name

    def format_name(self):
        last_name = self.last_name.lower().capitalize()
        first_name = self.first_name.lower().capitalize()
        name = last_name + ", " + first_name
        return name

    def add_to_list(self, date, data):
        data_pairing = {
            "date": date,
            "points": data
        }
        self.player_data.append(data_pairing)

    def write_to_json(self):
        json_string = json.dumps(self.player_data)
        print(json_string)

    def create_filename(self, file_extension=".json"):
        return self.last_name.lower().capitalize() + self.first_name.lower().capitalize() + "," + self.start_date + self.end_date + file_extension


c = Collector()
c.collect()

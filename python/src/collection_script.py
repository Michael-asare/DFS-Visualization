import sys
import pandas as pd
import lxml
import html5lib
import json


class Collector:
    # The url that will be edited to determine the date to be analyzed
    BASE_URL = "http://rotoguru1.com/cgi-bin/hyday.pl?mon=MONTH&day=DAY&year=YEAR&game=fd"
    DATE_BASE = "mon=MONTH&day=DAY&year=YEAR"
    # Table of Interest among located tables
    TOI = 5
    # Index of name information
    NAME_ROW = 1
    # Index of fanduel points information
    FANDUEL_POINTS_ROW = 2
    # Index of minutes information
    MINUTES_ROW = 7

    def __init__(self):
        self.first_name = sys.argv[1]
        self.last_name = sys.argv[2]
        self.start_date = sys.argv[3]
        self.end_date = sys.argv[4]
        self.player_data = []

    def collect(self):
        """
        Collects data for a range of dates for a player and then writes it to a json file

        :return:
        """

        # For each day
        # Parse data from a certain date
        # If data was parsed, add it to the list of player data
        # Write collected data to a json file

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
        """
        Formats the pandas api date to a easily indexed list

        :param panda_date: Timestamp
        :return: list with date information
        """
        year = panda_date.year
        month = panda_date.month
        day = panda_date.day
        date = [year, month, day]
        return date

    @staticmethod
    def format_url(year, month, day):
        """
        Creates a url based off of date data

        :param year: an int that represents the year
        :param month: an int that represents the month (1-12)
        :param day: an int that represents the day
        :return: a string that is a url
        """
        str_month = "mon=" + str(month)
        str_day = "day=" + str(day)
        str_year = "year=" + str(year)
        query = str_month + "&" + str_day + "&" + str_year
        return Collector.BASE_URL.replace(Collector.DATE_BASE, query)

    def parse(self, url):
        """
        Given a url, returns fanduel points for the person, if valid. If not valid, returns an empty string.

        :param url: a string that represents a url
        :return: the amount of fanduel points found for a player at the url. if not valid (NA/DNP/0 minutes) returns
        an empty string
        """

        # Tries to
        # Scan html for a table
        # If a false table is found, return an empty string
        # If no tables are found, return an empty string
        # Otherwise, proceed as normal
        # Look for the row that has a matching name
        # Check to see if the player had even played (no 0, NA, DNP for minutes)
        # If the player had played, return their fanduel points
        # Else, return an empty string

        try:
            dfs = pd.read_html(url)
            if len(dfs[Collector.TOI]) <= 1:
                return ""
            formatted_name = self.format_name()
            for i in range(len(dfs[Collector.TOI][Collector.NAME_ROW])):
                found_name = self.trim_name(dfs[Collector.TOI][Collector.NAME_ROW][i])
                found_minutes = dfs[Collector.TOI][Collector.MINUTES_ROW][i]
                if found_name.upper() == formatted_name:
                    if found_minutes != "0" and found_minutes != "NA" and found_minutes != "DNP":
                        found_fanduel_points = dfs[Collector.TOI][Collector.FANDUEL_POINTS_ROW][i]
                        return found_fanduel_points
            return ""
        except ValueError:
            return ""

    @staticmethod
    def trim_name(name):
        """
         Given a name (dfs site name), remove the carat if found

        :param name: name of a player, given in the dfs site format
        :return: trimmed dfs formatted name
        """
        if "^" in name:
            end = len(name) - 1
            return name[:end]
        return name

    def format_name(self):
        """
        Puts name into comparable format

        :return: comparable name (upper case & dfs style)
        """
        name = self.last_name + ", " + self.first_name
        return name.upper()

    def add_to_list(self, date, data):
        """
        Adds player data for a certain date to a list of dicts

        :param date: full format date
        :param data: fanduel points for a player
        :return:
        """
        data_pairing = {
            "date": date,
            "points": float(data)
        }
        self.player_data.append(data_pairing)

    def write_to_json(self):
        """
        Writes a player's data to a json file

        :return:
        """
        json_string = json.dumps(self.player_data)
        filename = self.create_filename()
        # Was originally r"..\..\data before java programming
        full_filename = r"..\data" + filename
        with open(full_filename, "w") as file:
            json.dump(self.player_data, file)

    def create_filename(self, file_extension=".json"):
        """
        Sets up the filename to be used for writing

        :param file_extension: defaults to json, optionally can be another file extenstion
        :return:
        """
        return "\\" + self.last_name.lower().capitalize() + "_" + self.first_name.lower().capitalize() + "_" + \
               Collector.cleanup_date(self.start_date) + "_" + Collector.cleanup_date(self.end_date) + file_extension

    @staticmethod
    def cleanup_date(date):
        """
        Turns a date into a format for filenaming

        :param date: Attribute date
        :return: Attribute date with dashes for slashes
        """
        return str(date).replace("/", "-")


c = Collector()
c.collect()

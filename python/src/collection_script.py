import sys
import pandas as pd
import lxml
import html5lib

# Gets the command line arguments for this file to run
# first_name = sys.argv[0]
# last_name = sys.argv[1]
# start_date = sys.argv[2]
# end_date = sys.argv[3]



# pd.options.display.max_columns = 999
# BASE_URL = "http://rotoguru1.com/cgi-bin/hyday.pl?mon=MONTH&day=DAY&year=YEAR&game=fd"
BASE_URL = "http://rotoguru1.com/cgi-bin/hyday.pl?mon=8&day=13&year=2020&game=fd"
dfs = pd.read_html(BASE_URL)
del dfs[5][2][0]
print(dfs[5][2])
print(pd.date_range(start='2/1/2020',end='8/21/2020'))

class Collector:
    # The url that will be edited to determine the date to be analyzed
    BASE_URL = "http://rotoguru1.com/cgi-bin/hyday.pl?mon=MONTH&day=DAY&year=YEAR&game=fd"

    def __init__(self, first_name, last_name, start_date, end_date):
        self.first_name = first_name
        self.last_name = last_name
        self.start_date = start_date
        self.end_date = end_date

    def collect(self):
        return

    def parse(self, url):
        return

    # need to edit
    def next_date(self):
        return

    def format_name(self):
        return

    def write_to_csv(self):
        return
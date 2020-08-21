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
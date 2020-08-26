import json

class Plotter:
    def __init__(self, data_filename, x_axis, y_axis):
        self.data_filename = data_filename
        self.x_axis = x_axis
        self.y_axis = y_axis
        self.y = []
        self.x = []

    def create_image(self):
        self.parse_JSON()

    def parse_json(self):

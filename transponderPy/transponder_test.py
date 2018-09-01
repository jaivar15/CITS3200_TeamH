#
#   Mimics transponder animal temperature given an input csv
#
#   Usage: python transponder_test.py -i <input file name> -o <output file name> -t <time between inputs (seconds)>
#
#   Created by: Alexander Slate
#

import csv, sys, getopt, time

def transpond(input_file, time_interval, output_file):
    with open(input_file, 'r') as input_csv:
        input = csv.reader(input_csv, delimiter=',')
        with open(output_file, 'a') as output_csv:
            output = csv.writer(output_csv,delimiter=',')
            for row in input:
                time.sleep(time_interval)
                print ("Output row: " + str(row))
                output.writerow (row)
                output_csv.flush()


def main(argv):

    input_file = ''
    output_file = ''
    time_interval = 1.0

    try:
        opts, args = getopt.getopt(argv, "t:i:o:", "")
    except getopt.GetoptError:
        print("transponder_test.py -t <time_interval> -i <input_file> -o <output_file>")

    for option, argument in opts:
        # if time interval was specified
        if (option == "-t"):
            time_interval = float(argument)
        # if input file was specified
        if (option == "-i"):
            input_file = argument
            print (input_file)
        if (option == "-o"):
            output_file = argument

    transpond(input_file, time_interval, output_file)

if __name__ == "__main__":
    main(sys.argv[1:])






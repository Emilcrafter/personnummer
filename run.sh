#!/bin/bash -e

function display_usage() {
  echo "Usage: $0 [-f] <input>"
  echo "  -f <file> : Input file containing numbers to classify"
  echo "     <input>: Number to classify"
  echo "  -h        : Display this help message"
  echo ""
  echo "Examples:"
  echo "  $0 -f input.txt"
  echo "  $0 2212732395"
}


if [ "$1" == "-h" ]; then
  display_usage
  exit 0
fi

while getopts ":f:" opt; do
  case $opt in
    f)
      input_file=$OPTARG
      ;;
    \?)
      echo "Invalid option: -$OPTARG" >&2
      display_usage
      exit 1
      ;;
    :)
      echo "Option -$OPTARG requires an argument." >&2
      display_usage
      exit 1
      ;;
  esac
done
shift $((OPTIND-1))

# Process number instantly if no input file is provided
if [ -z "${input_file}" ] && [ $# -eq 1 ]; then
  echo "Processing number: $1"
  echo "$1" | ./gradlew -q run
  exit 0
fi

if [ -z "$input_file" ]; then
  echo "Error: missing input file" >&2
  display_usage
  exit 1
fi

if [ ! -f "$input_file" ]; then
  echo "Error: file not found: $input_file" >&2
  display_usage
  exit 1
fi

echo "Processing numbers from file: $input_file"
./gradlew -q run < "$input_file"


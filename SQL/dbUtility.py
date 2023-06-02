import os
import subprocess
import sys

def export_database(derby_path, database_name, output_file_path):
    script_path = os.path.abspath(__file__)

    command = f"./bin/dblook -d 'jdbc:derby://localhost:1527/{database_name};user=APP;' -o '{output_file_path}'"
    subprocess.call(command, shell=True, cwd=derby_path)

def import_database(derby_path, database_name, input_file_path):
    script_path = os.path.abspath(__file__)

    command = f"java -jar '{derby_path}/lib/derbyrun.jar' ij connect \"jdbc:derby://localhost:1527/{database_name};user=APP;\"; run \"{input_file_path}\""
    subprocess.call(command, shell=True, cwd=derby_path)

# Ottieni gli argomenti dello script
args = sys.argv[1:]

if len(args) < 4:
    print("Usage: python database_tool.py <operation> <derby_path> <database_name> <path>")
    print("       operation: 'export' or 'import'")
    print("       derby_path: path of derby folder")
    print("       database_name: name of the database")
    print("       path: path for the output file (for export) or input file (for import)")
    sys.exit(1)

operation = args[0]
derby_path = args[1]
database_name = args[2]
path = args[3]

if operation == "export":
    export_database(derby_path, database_name, path)
elif operation == "import":
    import_database(derby_path, database_name, path)
else:
    print("Invalid operation. Please specify 'export' or 'import' as the operation.")

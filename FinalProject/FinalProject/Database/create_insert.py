import csv

part = {'adjective': 1, 'adverb': 2, 'noun': 3, 'verb': 4}

print('use hl3265;')

with open('words.txt', 'r') as file:
  csvfile = csv.reader(file, delimiter='\t')
  for line in csvfile:
    print('insert into word (word, part_id, definition) values' +
          ' ("{}", {}, "{}");'.format(line[0], part[line[1]], line[2]))

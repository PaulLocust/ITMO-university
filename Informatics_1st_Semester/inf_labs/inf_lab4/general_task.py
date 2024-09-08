# -*- coding: utf-8 -*-
import time

start_time = time.time()

fin = open('schedule.xml', encoding='utf-8')
fout = open('schedule.yaml', 'w+')

# read = 1, считывание названия тегов внутри < >
# read = 2, считывание текста заключенного между открывающим и закрывающим тегом <> здесь </>
# read = -1, в конец файла ничего не добавляется
read = 0
word = ''
for line in fin:
    xml = line  # каждая строка в xml файле
    yaml = ''  # строка, которая будет добавляться в yaml файл

    for i in range(len(xml)):

        if (read != 2 and xml[i] == '\t') or xml[i] == ' ' or xml[i] == '\n':
            yaml = yaml + xml[i]
        elif xml[i] == '<':
            if xml[i + 1] != '/':
                read = 1
            else:
                read = -1
        elif xml[i] == '>':
            if read == 1:
                yaml = yaml + ': '
                read = 2
            elif read == -1:
                if word != '':
                    yaml = yaml + '"' + word + '"'
                    word = ''
                read = 0
        elif read == 1:
            yaml = yaml + xml[i]
        elif read == 2:
            word = word + xml[i]
    fout.write(yaml)

fout.close()

end_time = time.time()

# Стократное время выполнения программы
execution_time = (end_time - start_time) * 100
print(f"General Стократное время выполнения программы: {execution_time} секунд")
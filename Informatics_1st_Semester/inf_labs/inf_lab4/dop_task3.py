# -*- coding: utf-8 -*-
import time


PUNCTUATION = r"""!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~"""


def get_open_tag_name(s: str) -> str:
    for i in range(len(s)):
        if s[i] == '>':
            return s[:i+1]

    raise ValueError('Key not found')


def get_close_tag_name(s: str) -> str:
    return s.replace('<', '</')


def get_text_without_tag(text: str, open_tag: str, close_tag: str):
    result = text.replace(open_tag, '').replace(close_tag, '').strip()

    if result:
        if not result.isalnum() or not result.isascii():
            result = f'\'{result}\''

    return result


def get_yaml_content(lines) -> str:
    open_tags = []

    yml_content = []
    for row in lines:

        tab_lenght = '\t' * len(open_tags)
        tmp_row = row.strip()

        if tmp_row[0] == '<' and tmp_row[1] not in PUNCTUATION:
            open_tag = get_open_tag_name(tmp_row)
            close_tag = get_close_tag_name(open_tag)
            text = get_text_without_tag(tmp_row, open_tag, close_tag)

            if tmp_row[-len(close_tag):] != close_tag:
                open_tags.append(close_tag)

            yml_content.append(f'{tab_lenght}{open_tag[1:-1]}: {text}')

        elif tmp_row[0] == '<' and tmp_row[1] == '/':
            if open_tags[-1] != tmp_row:
                ValueError('Bad XML file')
            open_tags.pop()

    if len(open_tags) != 0:
        ValueError('Bad XML file')

    return '\n'.join(yml_content)


def main():
    start_time = time.time()
    with open('schedule.xml', mode='r', encoding='utf-8') as f:
        xml_lines = f.readlines()

    yaml_content = get_yaml_content(xml_lines)

    with open('schedule.yaml', mode='w') as f:
        f.write(yaml_content)
    end_time = time.time()
    # Стократное время выполнения программы
    execution_time = (end_time - start_time) * 100
    print(f"dop 3 Стократное время выполнения программы: {execution_time} секунд")


if __name__ == '__main__':
    main()




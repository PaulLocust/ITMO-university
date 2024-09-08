# -*- coding: utf-8 -*-
import xml.etree.ElementTree as ET
from html import escape


def convert_xml_to_html(xml_file_path, html_file_path):
    # Парсинг XML файла
    tree = ET.parse(xml_file_path)
    root = tree.getroot()

    # Открытие HTML файла для записи
    with open(html_file_path, 'w', encoding='utf-8') as html_file:
        # Запись начальных тегов HTML
        html_file.write('<!DOCTYPE html>\n<html>\n<head>\n<title>XML to HTML Conversion</title>\n</head>\n<body>\n')

        # Рекурсивная функция для обхода элементов XML и их преобразования в HTML
        def convert_element_to_html(element):
            nonlocal html_file

            # Запись открывающего тега
            html_file.write(f'<{escape(element.tag)}>')

            # Запись атрибутов
            for key, value in element.attrib.items():
                html_file.write(f' {escape(key)}="{escape(value)}"')

            # Рекурсивный вызов для дочерних элементов
            for child in element:
                convert_element_to_html(child)

            # Запись закрывающего тега
            html_file.write(f'</{escape(element.tag)}>\n')

        # Начало обхода элементов XML
        for child in root:
            convert_element_to_html(child)

        # Запись закрывающих тегов HTML
        html_file.write('</body>\n</html>')

# Пример использования
convert_xml_to_html('schedule.xml', 'schedule.html')
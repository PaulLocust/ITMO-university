# -*- coding: utf-8 -*-
import xml.etree.ElementTree as ET
import yaml
import time

start_time = time.time()
# Чтение содержимого XML-файла
tree = ET.parse('schedule.xml')
root = tree.getroot()


# Функция для рекурсивного преобразования элементов XML в словарь
def xml_to_dict(element):
    if len(element) == 0:
        return element.text
    result = {}
    for child in element:
        child_data = xml_to_dict(child)
        if child.tag in result:
            if type(result[child.tag]) is list:
                result[child.tag].append(child_data)
            else:
                result[child.tag] = [result[child.tag], child_data]
        else:
            result[child.tag] = child_data
    return result


# Преобразование XML в словарь
xml_dict = xml_to_dict(root)

# Преобразование словаря в YAML
yaml_content = yaml.dump(xml_dict, default_flow_style=False)

# Запись результата в YAML-файл
with open('schedule.yaml', 'w') as yaml_file:
    yaml_file.write(yaml_content)

print("Конвертация завершена успешно.")

end_time = time.time()

# Стократное время выполнения программы
execution_time = (end_time - start_time) * 100
print(f"dop1 Стократное время выполнения программы: {execution_time} секунд")
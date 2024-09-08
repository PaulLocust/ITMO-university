def sign_addition(A, B):
    sign_combinations = [[1, 1], [-1, 1], [1, -1], [-1, -1]]  # все возможные комбинации знаков
    results = []

    for signs in sign_combinations:
        result = signs[0] * A + signs[1] * B  # знаковое сложение
        carry_flag = 0
        parity_flag = 0
        auxiliary_carry_flag = 0
        zero_flag = 0
        sign_flag = 0
        overflow_flag = 0

        # Определение флагов
        if result > 127 or result < -128:
            carry_flag = 1  # Устанавливаем Carry Flag в единицу, если происходит переполнение (значение не помещается в 8 бит)
        if bin(result).count('1') % 2 == 0:
            parity_flag = 1  # Устанавливаем Parity Flag в единицу, если число бит единиц четное
        if (A & 0x0F) + (B & 0x0F) > 0x0F:
            auxiliary_carry_flag = 1  # Устанавливаем Auxiliary Carry Flag в единицу, если происходит перенос между младшими полубайтами
        if result == 0:
            zero_flag = 1  # Устанавливаем Zero Flag в единицу, если результат равен нулю
        if result < 0:
            sign_flag = 1  # Устанавливаем Sign Flag в единицу, если результат отрицательный
        if (A > 0 and B > 0 and result < 0) or (A < 0 and B < 0 and result > 0):
            overflow_flag = 1  # Устанавливаем Overflow Flag в единицу, если происходит переполнение знакового разряда

        results.append((result, carry_flag, parity_flag, auxiliary_carry_flag, zero_flag, sign_flag, overflow_flag))

    return results


# Пример использования
A = 10
B = 5
results = sign_addition(A, B)
for result in results:
    print("Результат: ", result[0])
    print("Carry Flag: ", result[1])
    print("Parity Flag: ", result[2])
    print("Auxiliary Carry Flag: ", result[3])
    print("Zero Flag: ", result[4])
    print("Sign Flag: ", result[5])
    print("Overflow Flag: ", result[6])
    print()
# Открываем файл и считываем периоды колебаний
import math

with open('data.txt', 'r') as f:
    periods = [float(line.strip()) for line in f]
    periods_len = len(periods)


def arithmetic_mean(array):
    # Среднее арифметическое <t>_N
    sum = 0
    for i in array:
        sum += i
    ans = sum / len(array)
    return round(ans, 4)


def average_deviation():
    # t_i - <t>_N, с
    deviations = []
    for i in periods:
        deviations.append(round(i - arithmetic_mean(periods), 2))
    return deviations


def check_average_deviation():
    deviations = average_deviation()
    sum = 0
    for i in deviations:
        sum += i
    return sum


def average_deviation_pow():
    # Отклонение периода колебаний маятника от среднего значения периода колебаний
    # (t_i - <t>_N )^2, с2
    deviations = []
    for i in periods:
        deviations.append(round(pow(i - arithmetic_mean(periods), 2), 8))
    return deviations


def sample_standard_deviation():
    # среднеквадратичное отклонение среднего значения.
    # σ_<t>
    sum_deviations = sum(average_deviation_pow())
    return math.sqrt((1 / (periods_len * (periods_len - 1))) * sum_deviations)

def sample_standard_deviation_picked():
    # среднеквадратичное отклонение среднего значения.
    # σ_<N>
    sum_deviations = sum(average_deviation_pow())
    return math.sqrt((1 / (periods_len - 1)) * sum_deviations)


def max_distribution_density():
    # Максимальная плотность распределения
    # p_max
    return 1 / (sample_standard_deviation_picked() * math.sqrt(2 * math.pi))


def print_data():
    print("Среднее арифметическое всех результатов <t>N = " + str(arithmetic_mean(periods)) + "\n")

    print("t_i - <t>N = " + str(average_deviation()) + "\n")

    print("(t_i - <t>N)^2 = " + str(average_deviation_pow()) + "\n")

    print("σ_<t> = " + str(sample_standard_deviation()) + "\n")

    print("σ_<N> = " + str(sample_standard_deviation_picked()) + "\n")

    print("p_max = " + str(max_distribution_density()) + "\n")

    print("Данные:" + str(sorted(periods)) + "\n")


print_data()



#include <iostream>
#include <string>
#include <__chrono/day.h>
#include <__chrono/hh_mm_ss.h>
#include <__chrono/year_month_day.h>

// Variável estática - armazenada no segmento estático de memória
static std::string author = "Archimedes Fagundes Junior";

// Variável global - armazenada no segmento global de memória
std::string lastModification = "09/01/2025";

void printProgramInfo() {
    const auto now= std::chrono::system_clock::now();
    const auto today = floor<std::chrono::days>(now);
    const std::chrono::year_month_day date{today};

    constexpr auto offset = std::chrono::hours(-3);
    const auto local_now = now + offset;
    const auto time_since_midnight = local_now - floor<std::chrono::days>(local_now);
    const std::chrono::hh_mm_ss time{std::chrono::duration_cast<std::chrono::seconds>(time_since_midnight)};

    std::cout << std::format("Executado em {:02}/{:02}/{:04} às {:02}:{:02}:{:02} por {}.\nÚltima modificação em {}.\n",
        static_cast<unsigned>(date.day()),
        static_cast<unsigned>(date.month()),
        static_cast<int>(date.year()),
        static_cast<int>(time.hours().count()),
        static_cast<int>(time.minutes().count()),
        static_cast<int>(time.seconds().count()),
        author,
        lastModification);
}

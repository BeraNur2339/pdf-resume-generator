package com.resume;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Hayali 3 iş/staj deneyimi (HAVELSAN dahil)
        List<Experience> mockExperiences = Arrays.asList(
                new Experience("HAVELSAN", "Software Engineering Intern", "2025 (Yaz)", "Savunma sanayi projelerinde Java ve nesne yönelimli programlama (OOP) prensipleriyle modüler alt sistemlerin geliştirilmesi ve entegrasyon testlerinin icra edilmesi."),
                new Experience("Yazılım Çözümleri Ltd.", "Junior Java Developer Stajyeri", "2024 - 2025", "Arka plan servisleri, veri tabanı mimarileri ve RESTful API entegrasyonları için Java tabanlı modüler altyapı geliştirilmesi."),
                new Experience("İnovasyon Bilişim", "Yazılım Mühendisliği Stajyeri", "2024", "Simülasyon ve veri işleme modüllerinin nesne yönelimli mimaride kodlanması, performans optimizasyonu ve hata ayıklama süreçleri.")
        );

        // 2. sınıf ve güncel bilgileri içeren Resume nesnesi
        Resume myResume = new Resume(
                "Bera Nur Tursun",
                "Kırklareli Üniversitesi",
                "Yazılım Mühendisliği (2. Sınıf)",
                "",
                "tursunbera@gmail.com",
                "+90 555 000 00 00",
                "Kırklareli Üniversitesi Yazılım Mühendisliği 2. sınıf öğrencisiyim. C dili, veri yapıları, Linux sistem yönetimi, algoritma tasarımı ve nesne yönelimli programlama üzerine projeler geliştiriyorum. Lojistik rota yönetimi, konsol tabanlı syslog filtreleme ve min-heap zamanlayıcı algoritmaları gibi C projelerinin yanı sıra modern Java teknolojileriyle yazılım mimarileri üzerine çalışmaktayım.",
                "photo.jpg",
                mockExperiences
        );

        PdfGenerator generator = new PdfGenerator(myResume);
        try {
            generator.generatePdf("ozgecmis.pdf");
        } catch (FileNotFoundException e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }
}
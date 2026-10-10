# 🚀 Dynamic PDF Resume Generator (iText 7 & Java)

[![Java Version](https://img.shields.io/badge/Java-17%2B-orange?style=flat-square&logo=openjdk)](https://www.oracle.com/java/)
[![iText Core](https://img.shields.io/badge/iText-7.2%2B-blue?style=flat-square)](https://itextpdf.com/)
[![Build Tool](https://img.shields.io/badge/Maven-Managed-red?style=flat-square&logo=apachemaven)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)](LICENSE)

Enterprise düzeyinde tasarlanmış; nesne yönelimli programlama (OOP) prensiplerine, kapsülleme ilkelerine ve modüler katmanlı mimariye dayalı, ATS (Applicant Tracking System) uyumlu ve tek sayfalık dinamik özgeçmiş (CV) üreten yüksek performanslı bir Java konsol uygulamasıdır.

---

## 📋 İçindekiler
- [Proje Vizyonu ve Amaç](#-proje-vizyonu-ve-amaç)
- [Teknolojik Altyapı ve Kütüphaneler](#-teknolojik-altyapı-ve-kütüphaneler)
- [Proje Mimarisi ve Paket Yapısı](#-proje-mimarisi-ve-paket-yapısı)
- [Sınıf Tasarımı ve Sorumluluk Matrisi](#-sınıf-tasarımı-ve-sorumluluk-matrisi)
- [Öne Çıkan Mühendislik Özellikleri](#-öne-çıkan-mühendislik-özellikleri)
- [Kod Mimarisi Örnekleri](#-kod-mimarisi-örnekleri)
- [Kurulum ve Çalıştırma Rehberi](#-kurulum-ve-çalıştırma-rehberi)
- [Lisans](#-lisans)

---

## 🎯 Proje Vizyonu ve Amaç

Bu yazılım, statik ve manuel PDF oluşturma süreçlerini ortadan kaldırarak aday verilerini (`Name`, `Contact`, `Education`, `Experience`) nesne yönelimli yapıda işler. **iText 7 Core** kütüphanesinin düşük seviyeli API yeteneklerini kullanarak görsel hiyerarşisi güçlü, tipografik açıdan kusursuz ve ATS algoritmaları tarafından sorunsuz ayrıştırılabilen (`parsed`) endüstriyel standartlarda çıktılar üretir.

---

## 🛠️ Teknolojik Altyapı ve Kütüphaneler

| Bileşen | Teknoloji / Kütüphane | Sürüm | Açıklama |
| :--- | :--- | :--- | :--- |
| **Dil** | Java JDK | 17+ | Modern dil özellikleri ve OOP desteği |
| **PDF Motoru** | iText 7 Core | 7.2.x | Belge oluşturma, tablo yönetimi ve render işlemleri |
| **Build Sistemi**| Apache Maven | 3.8+ | Bağımlılık yönetimi ve lifecycle otomasyonu |
| **IDE** | IntelliJ IDEA | Latest | Geliştirme ve debug ortamı |
| **Sürüm Kontrol**| Git & GitHub | - | Kod versiyonlama ve uzak sunucu yönetimi |

---

## 🏛️ Proje Mimarisi ve Paket Yapısı

Uygulama, **Separation of Concerns (Sorumlulukların Ayrılması)** ve **Single Responsibility (Tek Sorumluluk)** prensipleri baz alınarak `com.resume` paketi altında modüler olarak tasarlanmıştır:

```text
pdf-resume-generator/
│
├── src/
│   ├── main/
│       ├── java/
│       │   └── com/
│       │       └── resume/
│       │           ├── Main.java          # Giriş noktası ve veri bağlama katmanı
│       │           ├── Resume.java        # Ana veri modeli (Encapsulation)
│       │           ├── Experience.java    # Deneyim veri modeli
│       │           └── PdfGenerator.java  # iText 7 Rendering ve Layout motoru
│       └── resources/
│           └── fonts/
│               └── Arial.ttf              # Gömülü tipografi dosyası (Türkçe karakter desteği)
│
├── pom.xml                                # Maven bağımlılık konfigürasyonu
└── README.md                              # Dokümantasyon

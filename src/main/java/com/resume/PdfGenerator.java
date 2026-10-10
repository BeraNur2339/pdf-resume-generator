package com.resume;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PdfGenerator {
    private Resume resume;

    public PdfGenerator(Resume resume) {
        this.resume = resume;
    }

    public void generatePdf(String dest) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Tek sayfaya tam sığması için optimize edilmiş kenar boşlukları
        document.setMargins(12f, 20f, 12f, 20f);

        // Türkçe Karakter Desteği (Arial)
        try {
            PdfFont font = PdfFontFactory.createFont("/System/Library/Fonts/Supplemental/Arial.ttf", PdfEncodings.IDENTITY_H, PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);
            document.setFont(font);
        } catch (IOException e) {
            System.out.println("Font yüklenemedi: " + e.getMessage());
        }

        // --- 1. ÜST BİLGİ (HEADER) TABLOSU ---
        Table headerTable = new Table(new float[]{20f, 80f});
        headerTable.setWidth(UnitValue.createPercentValue(100));

        // Sol Hücre: Büyütülmüş Fotoğraf
        Cell photoCell = new Cell();
        photoCell.setBorder(null);
        try {
            Image photo = new Image(ImageDataFactory.create(resume.getPhotoPath()));
            photo.setWidth(75f);
            photo.setHeight(75f);
            photoCell.add(photo);
        } catch (Exception e) {
            photoCell.add(new Paragraph("[Fotoğraf]").setFontSize(8.5f));
        }
        headerTable.addCell(photoCell);

        // Sağ Hücre: Ad Soyad, Bölüm ve İletişim Bilgileri
        Cell infoCell = new Cell();
        infoCell.setBorder(null);

        infoCell.add(new Paragraph(resume.getFullName()).setFontSize(16f).setBold().setFontColor(new DeviceRgb(30, 41, 59)));
        infoCell.add(new Paragraph(resume.getUniversity() + " | " + resume.getDepartment()).setFontSize(9f).setItalic().setFontColor(new DeviceRgb(71, 85, 105)));
        infoCell.add(new Paragraph("İLETİŞİM BİLGİLERİ").setBold().setFontSize(8f).setFontColor(new DeviceRgb(15, 23, 42)).setMarginTop(2f));
        infoCell.add(new Paragraph("• E-posta: " + resume.getEmail() + "  |  Tel: " + resume.getPhone()).setFontSize(8.5f));
        infoCell.add(new Paragraph("• LinkedIn: https://www.linkedin.com/in/bera-nur-t-811443385").setFontSize(8.5f));

        headerTable.addCell(infoCell);
        document.add(headerTable);
        document.add(new Paragraph("\n"));

        // --- 2. HAKKIMDA ---
        document.add(createSectionHeader("HAKKIMDA"));
        document.add(new Paragraph(resume.getSummary()).setFontSize(8.5f).setMarginBottom(1.5f));

        // --- 3. EĞİTİM BİLGİLERİ ---
        document.add(createSectionHeader("EĞİTİM BİLGİLERİ"));
        document.add(new Paragraph("• Kırklareli Üniversitesi — Yazılım Mühendisliği (2. Sınıf) | 2025 - Halen").setBold().setFontSize(8.5f));
        document.add(new Paragraph("• Ahmet Kabaklı Fen Lisesi — Mezuniyet Ortalaması: 94.60 / 100 | 2018 - 2022").setBold().setFontSize(8.5f).setMarginBottom(1.5f));

        // --- 4. TEKNİK YETKİNLİKLER VE LİNUX EĞİTİMİ ---
        document.add(createSectionHeader("TEKNİK YETKİNLİKLER VE LİNUX EĞİTİMİ"));
        document.add(new Paragraph("• Programlama & OOP: Java, C Programlama, Nesne Yönelimli Programlama").setFontSize(8.5f).setMarginBottom(1f));
        document.add(new Paragraph("• İşletim Sistemleri & Linux: Linux/Unix komut satırı, süreç yönetimi ve syslog analizi").setFontSize(8.5f).setMarginBottom(1f));
        document.add(new Paragraph("• Veri Yapıları: Hash Table, Graph (BFS), Min-Heap, Singly Linked List").setFontSize(8.5f).setMarginBottom(1.5f));

        // --- 5. KULÜPLER VE ETKİNLİKLER ---
        document.add(createSectionHeader("KULÜPLER VE ETKİNLİKLER"));
        document.add(new Paragraph("• Kırklareli Üniversitesi TEKNOFEST Kulübü — Core Team Üyesi (2025 - Halen)").setFontSize(8.5f));
        document.add(new Paragraph("• Deneyap Türkiye — Proje Danışmanı (2026 - Halen)").setFontSize(8.5f).setMarginBottom(1.5f));

        // --- 6. İŞ VE STAJ DENEYİMLERİ ---
        document.add(createSectionHeader("İŞ VE STAJ DENEYİMLERİ"));
        for (Experience exp : resume.getExperiences()) {
            document.add(new Paragraph(exp.getCompany() + " — " + exp.getPosition() + " (" + exp.getDuration() + ")").setBold().setFontSize(8.5f).setFontColor(new DeviceRgb(15, 23, 42)));
            document.add(new Paragraph(exp.getDescription()).setFontSize(8.5f).setMarginBottom(1f));
        }

        // --- 7. ÖNE ÇIKAN PROJELER ---
        document.add(createSectionHeader("ÖNE ÇIKAN PROJELER"));
        document.add(new Paragraph("• Lojistik ve Rota Yönetim Programı (C): Hash Table ve Graph (BFS) ile en kısa rota hesabı.").setFontSize(8.5f).setMarginBottom(1f));
        document.add(new Paragraph("• Linux Syslog Filtreleme Modülü (C): Bağlı liste ve dinamik bellek yönetimiyle günlük filtreleme.").setFontSize(8.5f).setMarginBottom(1f));
        document.add(new Paragraph("• Min-Heap Zamanlayıcı Algoritması (C): RFC 9213 uyumlu öncelikli istek kuyruğu yöneticisi.").setFontSize(8.5f).setMarginBottom(1.5f));

        // --- 8. SERTİFİKALAR ---
        document.add(createSectionHeader("SERTİFİKALAR"));
        document.add(new Paragraph("• Java'ya Giriş (BTK Akademi) | Veri Yapıları (BTK Akademi) | C Programlama (BTK Akademi)").setFontSize(8.5f).setMarginBottom(1.5f));

        // --- 9. YABANCI DİLLER ---
        document.add(createSectionHeader("YABANCI DİLLER"));
        document.add(new Paragraph("• Türkçe (Ana Dil)  |  İngilizce (İleri Düzey)  |  Almanca (Temel Düzey)").setFontSize(8.5f).setMarginBottom(1.5f));

        // --- 10. HOBİLER VE İLGİ ALANLARI ---
        document.add(createSectionHeader("HOBİLER VE İLGİ ALANLARI"));
        document.add(new Paragraph("• Seyahat etmek, şiir yazmak, satranç oynamak, klasik gitar icra etmek.").setFontSize(8.5f));

        document.close();
        System.out.println("PDF başarıyla oluşturuldu: " + dest);
    }

    private Paragraph createSectionHeader(String titleText) {
        return new Paragraph(titleText)
                .setBold()
                .setFontSize(9.5f)
                .setFontColor(new DeviceRgb(30, 41, 59))
                .setMarginTop(2.5f)
                .setMarginBottom(1f);
    }
}
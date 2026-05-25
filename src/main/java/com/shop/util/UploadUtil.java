package com.shop.util;

import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

public class UploadUtil {
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of(".jpg", ".jpeg", ".png", ".webp", ".gif");

    private UploadUtil() {}

    public static String saveImage(Part part, String uploadPath) throws IOException {
        if (part == null || part.getSize() == 0) {
            throw new IOException("请选择商品图片");
        }
        String extension = extensionOf(part.getSubmittedFileName());
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new IOException("仅支持 jpg、png、webp、gif 图片");
        }
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists() && !uploadDir.mkdirs()) {
            throw new IOException("无法创建上传目录");
        }
        String filename = UUID.randomUUID() + extension;
        part.write(uploadPath + File.separator + filename);
        return "uploads/" + filename;
    }

    private static String extensionOf(String filename) {
        if (filename == null) {
            return "";
        }
        int index = filename.lastIndexOf('.');
        return index >= 0 ? filename.substring(index).toLowerCase(Locale.ROOT) : "";
    }
}

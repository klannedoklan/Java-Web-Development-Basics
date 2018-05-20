package javache.http;

public final class HTMLEncoder {
    private HTMLEncoder() { }

    public static String escapeHTML(String str) {
        StringBuilder stringBuilder = new StringBuilder(Math.max(16, str.length()));
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c > 127 || c == '"' || c == '<' || c == '>' || c == '&') {
                stringBuilder.append("&#");
                stringBuilder.append((int) c);
                stringBuilder.append(';');
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }
}

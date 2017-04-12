package jhk.nytimes_mobile.Objects;


/**
 * Time News Api Response Mapping Class [ Multi Media Data ]
 */
public class MultiMediaData{
    public String url = "";
    public String format = "";
    public int height = 0;
    public int width = 0;
    public String type = "";
    public String subtype = "";
    public String caption = "";
    public String copyright = "";

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("URL : %s, ",url));
        sb.append(String.format("Format : %s, ",format));
        sb.append(String.format("Height : %d, ",height));
        sb.append(String.format("Width : %d, ",width));
        sb.append(String.format("Type : %s, ",type));
        sb.append(String.format("Sub Type : %s, ",subtype));
        sb.append(String.format("Caption : %s, ",caption));
        sb.append(String.format("Copyright : %s, ",copyright));
        return  sb.toString();
    }
}

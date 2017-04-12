package jhk.nytimes_mobile.Objects;

import java.util.List;

/**
 * Time News Api Response Mapping Class [ Story Data ]
 */
public class StoryData {
    public String section = "";
    public String subsection = "";
    public String title = "";
    public String abstractString = "";
    public String url = "";
    public String byLine = "";
    public String item_type = "";
    public String updated_date;
    public String created_date;
    public String published_date;
    public String material_type_facet = "";
    public String kicker = "";
    public List<String> des_facet;
    public List<String> org_facet;
    public List<String> per_facet;
    public List<String> geo_facet;
    public List<MultiMediaData> multimedia;
    public String short_url = "";

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Section : %s, ",section));
        sb.append(String.format("Subsection : %s, ",subsection));
        sb.append(String.format("Title : %s, ",title));
        sb.append(String.format("Abstract : %s, ",abstractString));
        sb.append(String.format("URL : %s, ",url));
        sb.append(String.format("By Line : %s, ",byLine));
        sb.append(String.format("Item Type : %s, ",item_type));
        sb.append(String.format("Updated Date : %s, ",updated_date));
        sb.append(String.format("Created Date : %s, ",created_date));
        sb.append(String.format("Published Date : %s, ",published_date));
        sb.append(String.format("Material Type Facet : %s, ",material_type_facet));
        sb.append(String.format("Kicker : %s, ",kicker));
        sb.append(String.format("Des Facet Count : %d, ",des_facet.size()));
        sb.append(String.format("Org Facet Count : %d, ",org_facet.size()));
        sb.append(String.format("Per Facet Count : %d, ",per_facet.size()));
        sb.append(String.format("Geo Facet Count : %d, ",geo_facet.size()));
        sb.append(String.format("Multimedia Count: %d, ",multimedia.size()));
        sb.append(String.format("Short url : %s",short_url));
        return sb.toString();
    }
}



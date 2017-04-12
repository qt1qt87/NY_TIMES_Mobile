package jhk.nytimes_mobile.Objects;

import java.util.ArrayList;

/**
 * Time News Api Response Mapping Class
 */
public class API_Result {
    public String status;
    public String copyright;
    public String section;
    public String last_updated;
    public int num_results = 0;
    public ArrayList<StoryData> results;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Status : %s, ",status));
        sb.append(String.format("Copy Right : %s, ",copyright));
        sb.append(String.format("Section : %s, ",section));
        sb.append(String.format("Last Updated : %s, ",last_updated));
        sb.append(String.format("Num Results : %d, ",num_results));
        return  sb.toString();
    }
}

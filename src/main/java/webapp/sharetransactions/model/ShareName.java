package webapp.sharetransactions.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@SuppressWarnings("serial")
@XmlRootElement
public class ShareName implements Serializable {
    private String shareName;
    private String webSubPage;

    public ShareName(){}

    public ShareName(String shareName, String webSubPage) {
        this.shareName = shareName;
        this.webSubPage = webSubPage;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    public String getWebSubPage() {
        return webSubPage;
    }

    public void setWebSubPage(String webSubPage) {
        this.webSubPage = webSubPage;
    }
}

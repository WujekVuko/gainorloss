package webapp.sharetransactions.domain;

import java.util.Arrays;
import java.util.Random;


public class ChartData {
    private String label;
    private float[] data;
    private boolean fill;
    private String borderColor;
    private float lineTension;

    public ChartData(String label, float[] data) {
        this.label = label;
        this.data = data;
        this.fill = false;
        this.lineTension = (float) 0.1;
        this.borderColor = generateRandomColor();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public float[] getData() {
        return data;
    }

    public void setData(float[] data) {
        this.data = data;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public float getLineTension() {
        return lineTension;
    }

    public void setLineTension(float lineTension) {
        this.lineTension = lineTension;
    }

    private String generateRandomColor(){
        Random random = new Random();
        return "#" + Integer.toHexString(random.nextInt(0xFFFFFF));
    }


    @Override
    public String toString() {
        return "ChartData{" +
                "label='" + label + '\'' +
                ", data=" + Arrays.toString(data) +
                ", fill=" + fill +
                ", backgroundColor='" + borderColor + '\'' +
                '}';
    }
}

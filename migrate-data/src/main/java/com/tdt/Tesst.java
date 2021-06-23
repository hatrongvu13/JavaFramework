package com.tdt;

public class Tesst {
    public static void main(String[] args) {
        String videoString = "<div class=\"fileShare\">\n" +
                "    <div id=\"Preview{{previewId}}-0\" class=\"clearfix isPreviewable previewable10\">\n" +
                "        <div id=\"MediaContent3-0\" class=\"mediaContent PlayerContianer\" style=\"padding: 5px\">\n" +
                "            <video src=\"{{urlVideo}}\"\n" +
                "                   controls=\"controls\" class=\"videoContent\" style=\"width: 100%\">\n" +
                "                your browser does not support the video tag\n" +
                "            </video>\n" +
                "        </div>\n" +
                "    </div>" +
                "</div>";
        String id = "12";
        String urlVideo = "/rest/media/file/2021-02-24_16h33_10.mp4";
        System.out.println(videoString
                .replace("{{previewId}}", id)
                .replace("{{urlVideo}}", urlVideo));
    }
}

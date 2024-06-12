package gof_ProxyP;

/**
 * 실제 썸네일 클래스
 */
public class RealThumbnail implements Thumbnail {

    private String title;
    private String movieUrl;
    
    public RealThumbnail(String title, String movieUrl) {
        this.title = title;
        this.movieUrl = movieUrl;

        // URL로부터 영상을 다운받는 작업 - 시간 소모
        System.out.println(movieUrl + "로부터 " + title + " 의 영상 데이터 다운");
    }

    @Override
    public void showTitle() {
        System.out.println("제목 :" + title);
    }

    @Override
    public void showPreview() {
        System.out.println(title + " 의 썸네일 재생");
    }

}

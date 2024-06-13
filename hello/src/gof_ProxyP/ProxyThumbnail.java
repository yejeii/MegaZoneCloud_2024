package gof_ProxyP;

/**
 * 대리인 클래스 - 가벼운 작업을 위한 클래스
 * 
 * 영상을 받아오지 않기 때문에 생성하는 데 시간이 걸리지 않으며 객체 자체도 가볍다.
 * 그러나 Thumbnail 인터페이스를 구현했기 때문에 showPreview() 를 가지고 있다.
 * 그러나 직접 실행치 않고 그 책임을 RealThumbnail 클래스에게 위임한다.
 */
public class ProxyThumbnail implements  Thumbnail{

    private String title;
    private String movieUrl;

    private RealThumbnail realThumbnail;

    /* ProxyThumbnail 가 생성될 때에는 realThumbnail null 상태
     * showTitle() 와 같은 가벼운 작업은 프록시가 수행
     * 영상 데이터를 필요로 할 때, 즉 showPreview() 를 실행할 때 realThumbnail 에게 넘겨줄 
     */
    public ProxyThumbnail(String title, String movieUrl) {
        this.title = title;
        this.movieUrl = movieUrl;
    }

    @Override
    public void showTitle() {
        System.out.println("제목 : " + title);
    }

    @Override
    public void showPreview() {
        if(realThumbnail == null) {
            realThumbnail = new RealThumbnail(title, movieUrl);
        }
        realThumbnail.showPreview();
    }

}

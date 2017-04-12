# New York Times Mobile Viewer
New York Times Mobile Viewer Android Application

# Build
Target Android API Level 19 (Android 4.4 Kitkat )

# Features
 - New York Times Api 호출 및 Response Data 파싱
 - Grid View를 이용한 뉴스 기사 리스트 출력
 - Async Task를 이용한 Web Image 획득
 - Image Chaching을 통한 성능 향상
 - 뉴스 기사 타이틀 클릭시 상세 보기로 이동

# Activities
- StoryList Activity
- Story Activity

# Open Source
- Android StaggeredGrid ( etsy ) - 높이가 다른 Grid View를 그리기 위해 사용
- Gson - Api Response Data 파싱을 위해 사용

# Fixed Issue
- Gson을 이용한 Json 맵핑 과정에서 Response Data에 "Abstact"라는 필드가 존재하여 맵핑 클래스 설계에 문제 발생
	- 맵핑 클래스에 abstact 대신 abstactString 변수를 선언 하고 Response Data의 "abstact" 문자열을 "abstactString"으로 치환 후 파싱하도록 수정
- WebView에서 페이지를 Load하는 중 Redicretion된 Url을 WebView가 자체적으로 암시적 인텐트(Action_View)를 이용해 브라우저로 오픈하는 문제 발생
	-  WebViewClient의 shouldOverrideUrlLoading() 함수를 오버라이딩 하여 해결
- GridView를 빠르게 움직일 때 하나의 Image View에 대해 이미지를 로드하는 Async Task가 여러개 생성 되는 문제 발생
    - GridViewItem클래스에 Async Task를 종속 시켜 새로운 이미지를 로드하는 Task가 시작 되기 전에 기존 Task를 Cancel하도록 수정
- GridView가 스크롤 될 때 마다 마다 이미지를 로드하다 보니 빈 이미지가 자주 표시되는 문제 발생
     - Static 변수로 Image Cahce를 두어 로드된 적 있는 이미지일 경우 캐시 이미지를 사용하도록 변경
     
# ISSUE
- GridView를 빠르게 움직일 때 AsyncTask Cancel 기능으로 인해 BitmapFactory.decodeStream() 함수 내부에서 thread interrupted Exception 발생
	- 현재로썬 BitmapFactory.decodeStream() 함수가 Exception을 외부로 throw하지 않기 때문에 예외 처리를 하지 못하고 있음.

# TODO
- 네트워크 상태 체크 로직 강화
	- 현재 StoryList를 조회 하기 전에만 인터넷 상태를 체크하여 메세지를 표시하는데 브로드케스트를 이용하여 네트워크 상태 변경에 따라 즉각적으로 UI를 통해 표시되는것이 좋을것 같다고 생각함
- 캐시 로직 고도화
     - 효율적으로 캐시 버퍼 메모리를 사용하도록 변경
     - 최대 버퍼 크기 설정하고 버퍼가 가득 차게 되면 최근 사용 기록이 없는 이미지 캐시를 삭제
- API Result 맵핑 객체로 필드중 Enum으로 변경할수 있는 것들을 변경
	-  맵핑 객체로 설계하는 과정에서 몇가지 Enum형태가 적당한 필드들이 있었는데 Enum 전체 리스트를 파악하지못해 우선 String으로 구현함
- Story List Activity에 뉴스 Section 필터 추가
- 키워드 딥 러닝을 통한 추천 뉴스 표시 기능 추가
- Story 화면에서 스와이프를 통한 뒤로가기 기능 추가
- 북마크 기능 추가
- 공유하기 기능 추가

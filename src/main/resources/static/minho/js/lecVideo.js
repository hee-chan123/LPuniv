// DIV에 YouTube Iframe API 플레이어 호출
let CCIM_videoID = document.querySelector("#board_wrap_videoId").getAttribute("videoId");
function onYouTubeIframeAPIReady() {
        player = new YT.Player('youtubeVideo', {   // 호출할 위치의 div 컴포넌트 id
        width: '850',               // 가로 사이즈
        height: '500',               // 세로 사이즈
        videoId: CCIM_videoID,            // 비디오ID
        playerVars: {
            disablekb: 1,              // 키보드 입력 제한
            rel: 0
        }
        // events: {
        //     'onReady': onPlayerReady,
        //     'onStateChange': onPlayerStateChange,
        //     'onPlaybackRateChange': onPlayerPlaybackRateChange
        // }
    });
}
// 플레이어가 준비되었을 때 실행되는 콜백 함수
function onPlayerReady(event) {
    // 플레이어가 준비되면 이곳에 코드를 추가합니다.
    // 예: event.target.playVideo();
}

// 플레이어 상태가 변경될 때 실행되는 콜백 함수
function onPlayerStateChange(event) {
    // 플레이어 상태가 변경될 때 이곳에 코드를 추가합니다.
    // 예: 동영상이 종료될 때의 처리
}

//시간 기록
function updatePosition() {
    schs_endpo = player.getCurrentTime();
    schs_fnpo = schs_fnpo > schs_endpo ? schs_fnpo : schs_endpo; // 두개 변수 비교해서 참일시, 거짓일시 리턴 값

    //진도체크 테이블에 저장
}
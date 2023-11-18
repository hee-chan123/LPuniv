function getVideoDuration() {
    // YouTube API 키
    const apiKey = 'AIzaSyA3SrPBf2CcVVn4s_KL-G022kBSj8AJ7Ik';

    // YouTube 동영상 ID를 입력받는 input 요소
    const videoIdInput = document.getElementById('videoID');    // video ID
    const setDuration = document.getElementById('setDuration'); // duration (hh:mi:ss)
    const viewDuration = document.getElementById('viewDuration'); // duration (s)

    // 입력된 동영상 ID
    const videoId = videoIdInput.value;

    // YouTube API를 사용하여 동영상 정보 가져오기
    fetch(`https://www.googleapis.com/youtube/v3/videos?id=${videoId}&key=${apiKey}&part=contentDetails`)
        .then(response => response.json())
        .then(data => {
            // 동영상의 재생 시간 정보를 ISO 8601 형식에서 파싱
            const duration = data.items[0].contentDetails.duration;
            const parseISODuration = parseISO8601Duration(duration);
            const parseDuration = `${String(parseISODuration.hours).padStart(2, '0')}:${String(parseISODuration.minutes).padStart(2, '0')}:${String(parseISODuration.seconds).padStart(2, '0')}`;

            // 재생 시간을 결과를 표시할 input 요소의 value에 설정
            setDuration.value = duration;
            viewDuration.value = parseDuration;
            console.log('Video Duration:', duration);


        })
        .catch(error => {
            alert('해당 영상 존재하지 않습니다. ID를 확인해주세요.');
            console.error('Error fetching video data:', error);
        });
}

// ISO 8601 형식의 동영상 재생 시간을 파싱하는 함수
function parseISO8601Duration(duration) {
    const matches = duration.match(/PT(\d+H)?(\d+M)?(\d+S)?/);

    const hours = parseInt(matches[1]) || 0;
    const minutes = parseInt(matches[2]) || 0;
    const seconds = parseInt(matches[3]) || 0;

    return {
        hours,
        minutes,
        seconds
    };
}
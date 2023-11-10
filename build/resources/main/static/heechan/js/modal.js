$(document).ready(function() { //모달 시작
    $('#myModal').on('show.bs.modal', function () {
        $('#modalContent').load('/message/recmsg');
    });
});

function changePage(pageUrl) { //모달 화면 전환
    $('#modalContent').load(pageUrl);
}

function recDelMsg(msgId) { // 받은 메시지 삭제
    if (confirm('메시지를 삭제하시겠습니까?')) {
        $.ajax({
            type: 'POST',
            url: '/message/recdel',
            data: { msgId: msgId },
            success: function(data) {
                $('#modalContent').load('/message/recmsg');
            },
            error: function(error) {
                console.error('삭제 중 오류가 발생했습니다.', error);
            }
        });
    }
}

function senDelMsg(msgId) { //보낸 메시지 삭제
    if (confirm('메시지를 삭제하시겠습니까?')) {
        $.ajax({
            type: 'POST',
            url: '/message/sendel',
            data: { msgId: msgId },
            success: function(data) {
                $('#modalContent').load('/message/senmsg');
            },
            error: function(error) {
                console.error('삭제 중 오류가 발생했습니다.', error);
            }
        });
    }
}

function recycleRecMsg(msgId) { //휴지통에서 받은 메시지 복구
    if (confirm('메시지를 복구하시겠습니까?')) {
        $.ajax({
            type: 'POST',
            url: '/message/recyclerecmsg',
            data: { msgId: msgId },
            success: function(data) {
                $('#modalContent').load('/message/recycle');
            },
            error: function(error) {
                console.error('삭제 중 오류가 발생했습니다.', error);
            }
        });
    }
}

function recycleSenMsg(msgId) { //휴지통에서 보낸 메시지 복구
    if (confirm('메시지를 복구하시겠습니까?')) {
        $.ajax({
            type: 'POST',
            url: '/message/recyclesenmsg',
            data: { msgId: msgId },
            success: function(data) {
                $('#modalContent').load('/message/recycle');
            },
            error: function(error) {
                console.error('삭제 중 오류가 발생했습니다.', error);
            }
        });
    }
}

function searchMsg(div) { //검색
    let searchInput = document.getElementById("searchInput").value;
    let searchOp = document.getElementById("searchOp").value;

    if(div === 'rec'){
        let url = `/message/recmsg?searchInput=${searchInput}&searchOp=${searchOp}&div=${div}`;
        $.ajax({
            type: "GET",
            url: "/message/recmsg",
            data: {
                searchInput: searchInput,
                searchOp: searchOp,
                div: div

            },
            success: function(data) {
                $('#modalContent').load(url);
            },
            error: function(error) {
                console.log("검색 중 오류가 발생했습니다.", error);
            }
        });
    } else if(div === 'sen'){
        let url = `/message/senmsg?searchInput=${searchInput}&searchOp=${searchOp}&div=${div}`;
        $.ajax({
            type: "GET",
            url: "/message/senmsg",
            data: {
                searchInput: searchInput,
                searchOp: searchOp,
                div: div
            },
            success: function(data) {
                $('#modalContent').load(url);
            },
            error: function(error) {
                console.log("검색 중 오류가 발생했습니다.", error);
            }
        });
    } else if(div === 'recycle'){
        let url = `/message/recycle?searchInput=${searchInput}&searchOp=${searchOp}&div=${div}`;
        $.ajax({
            type: "GET",
            url: "/message/recycle",
            data: {
                searchInput: searchInput,
                searchOp: searchOp,
                div: div
            },
            success: function(data) {
                $('#modalContent').load(url);
            },
            error: function(error) {
                console.log("검색 중 오류가 발생했습니다.", error);
            }
        });
    }
}
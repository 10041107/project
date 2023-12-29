// 지도 구현

/* 내 현재위치의 위도 경도 계산 */
navigator.geolocation.getCurrentPosition((position) => {
    console.log("qwe")
    /* 위도 경도 변수에 담기 */
    latitude = position.coords.latitude;
    longitude = position.coords.longitude;

    /* 다중 마커와 정보 함수 표현 (제이쿼리) */
    $(function() {
        initMap();
    });
    function initMap() {

        var areaArr = new Array();  // 지역을 담는 배열 ( 지역명/위도경도 ) /* 추후에는 값을 db에서 받아와서 구현.. */

        areaArr.push(
            /*지역구 이름*/			/*위도*/					/*경도*/
            {information : '강남' , lat : '37.4959854' , lng : '127.0664091'},
            {information : '강동' , lat : '37.5492077' , lng : '127.1464824'},
            {information : '강북' , lat : '37.6469954' , lng : '127.0147158'},
            {information : '강서' , lat : '37.5657617' , lng : '126.8226561'},
            {information : '관악' , lat : '37.4603732' , lng : '126.9536086'},
            {information : '광진' , lat : '37.5574120' , lng : '127.0796211'},
            {information : '구로' , lat : '37.4954856' , lng : '126.858121' },
            {information : '금천' , lat : '37.4600969' , lng : '126.9001546'},
            {information : '노원' , lat : '37.6377533' , lng : '127.0754623'},
            {information : '도봉' , lat : '37.6658609' , lng : '127.0317674'},
            {information : '동대문' , lat : '37.5838012' , lng : '127.0507003'},
            {information : '동작' , lat : '37.4965037' , lng : '126.9443073'},
            {information : '마포' , lat : '37.5676507' , lng : '126.8854549'},
            {information : '서대문' , lat : '37.5820369' , lng : '126.9356665'},
            {information : '서초' , lat : '37.4769528' , lng : '127.0378103'},
            {information : '성동' , lat : '37.5506753' , lng : '127.0409622'},
            {information : '성북' , lat : '37.606991'  , lng : '127.0232185'},
            {information : '송파' , lat : '37.5177941' , lng : '127.1127078'},
            {information : '양천' , lat : '37.5270616' , lng : '126.8561534'},
            {information : '영등포' , lat : '37.520641'  , lng : '126.9139242'},
            {information : '용산' , lat : '37.5311008' , lng : '126.9810742'},
            {information : '은평' , lat : '37.6176125' , lng : '126.9227004'},
            {information : '종로' , lat : '37.5990998' , lng : '126.9861493'},
            {information : '중구' , lat : '37.5579452' , lng : '126.9941904'},
            {information : '중랑구' , lat : '37.598031'  , lng : '127.092931' }
        );



        let markers = new Array(); // 마커 정보를 담는 배열
        let infoWindows = new Array(); // 정보창을 담는 배열

        var map = new naver.maps.Map('map', {
            center: new naver.maps.LatLng(latitude, longitude), //지도 시작 지점
            zoom: 13,
            mapTypeControl: true
        });

        for (var i = 0; i < areaArr.length; i++) {
            // 지역을 담은 배열의 길이만큼 for문으로 마커와 정보창을 채워주자 !

            var marker = new naver.maps.Marker({
                map: map,
                title: areaArr[i].information, // 지역구 이름
                position: new naver.maps.LatLng(areaArr[i].lat , areaArr[i].lng) // 지역구의 위도 경도 넣기
            });

            /* 정보창 */
            var infoWindow = new naver.maps.InfoWindow({
                content: '<div style="width:200px;text-align:center;padding:10px;"><b>' + areaArr[i].information + '</b><br> - 네이버 지도 - </div>'
            }); // 클릭했을 때 띄워줄 정보 HTML 작성

            markers.push(marker); // 생성한 마커를 배열에 담는다.
            infoWindows.push(infoWindow); // 생성한 정보창을 배열에 담는다.
        }
        function getClickHandler(seq) {

            return function (e) {  // 마커를 클릭하는 부분
                var marker = markers[seq], // 클릭한 마커의 시퀀스로 찾는다.
                    infoWindow = infoWindows[seq]; // 클릭한 마커의 시퀀스로 찾는다

                if (infoWindow.getMap()) {
                    infoWindow.close();
                } else {
                    infoWindow.open(map, marker); // 표출
                }
            }
        }
        for (var i=0, ii=markers.length; i<ii; i++) {
            console.log(markers[i] , getClickHandler(i));
            naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i)); // 클릭한 마커 핸들러
        }

        /* (좌측상단 버튼) 내위치로 지도 이동 */
        var locationBtnHtml = '<a href="#" class="mapBtn"><span class="mapBtnText">내 위치로</span></a>';

        var customControl = new naver.maps.CustomControl(locationBtnHtml, {
            position: naver.maps.Position.TOP_LEFT
        });

        naver.maps.Event.once(map, 'init', function() {
            customControl.setMap(map);

            naver.maps.Event.addDOMListener(customControl.getElement(), 'click', function() {
                map.setCenter(new naver.maps.LatLng(latitude, longitude));
            });
        });
    }

})

/* 다중 마커 표현 */

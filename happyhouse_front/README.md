# [Vue Light Bootstrap Dashboard](http://vuejs.creative-tim.com/vue-light-bootstrap-dashboard) [![version][version-badge]][CHANGELOG] [![license][license-badge]][LICENSE]

> Admin dashboard based on light bootstrap dashboard UI template + vue-router

This project is a vue version of [Light bootstrap dashboard](https://www.creative-tim.com/product/light-bootstrap-dashboard)
designed for vue js. The dashboard includes Bootstrap 4, vue-router, chartist, google-maps and several other plugins/components.

Check the [Live Demo here](http://vuejs.creative-tim.com/vue-light-bootstrap-dashboard).

![](public/Dashboard.PNG)
## :rocket: Getting started

Vue Light Bootstrap Dashboard is built on top of Bootstrap 4, Vuejs and Vue-router. To get started do the following steps:
1. Download the project
2. Make sure you have node.js (https://nodejs.org/en/) installed
3. Type `npm install` in the source folder where `package.json` is located
4. Type `npm run dev` to start the development server

The repo uses [vue-cli](https://github.com/vuejs/vue-cli) scaffolding which takes care of the development setup with webpack and all the necessary modern tools to make web development faster and easier.

## [Documentation](https://demos.creative-tim.com/vue-light-bootstrap-dashboard/documentation/#/buttons)

## :cloud: Build Setup

### install dependencies
`npm install`
### serve with hot reload at localhost:8000
`npm run dev`
### build for production with minification
`npm run build`
### run unit tests
`npm run unit`
### run and watch unit tests
`npm run unit:watch`

## :clipboard: Contribution guide
* `npm install` or `yarn install`
* Please don't use jQuery or jQuery based plugins since there are many pure Vue alternatives

For detailed explanation on how things work, checkout the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).

[CHANGELOG]: ./CHANGELOG.md
[LICENSE]: ./LICENSE.md
[version-badge]: https://img.shields.io/badge/version-1.0.0-blue.svg
[license-badge]: https://img.shields.io/badge/license-MIT-blue.svg


### ISSUE관리

---

# ssafy 5th; 정범진, 조세연, 최경운

## 0520...

게시판
안심병원
아파트 거래내역

(리스트, 검색기능 포함) vue를 통해 구현, 백엔드 restController 적용
페이징처리(페이지 이동했다 돌아왔을 때도 검색내역이 유지되도록 처리)

## 0521...

로그인처리 - jwt토큰을 사용한 로그인처리, 로컬 스토리지 사용하여 토큰관리
---ISSUE : (페이지 새로고침 시 토큰 만료됨, 링크를 통한 페이지 이동시에는 상관없음)

지도 - 카카오map(동별, 아파트별 마커 적용 + 리스트 출력), 범위지정
(API사용시 async와 promise를 이용한 순서처리) (기술문서 참고)

아파트 거래내역에 좋아요기능 추가

vue템플릿으로 FE 정리

## 0522...

관심아파트 관리 : 
    관심 아파트 등록(좋아요) 시 데이터베이스에 userid와 aptno 저장 (다중키, 외래키),
    이미 등록된 매물에 중복 클릭시 데이터베이스에서 제거
    => 유저별 관심아파트 볼 수 있도록 관심아파트 페이지 추가
---ISSUE : $store.state에 저장된 userid값을 기반으로 백엔드 서버에 파라미터를 전달하였는데,
            새로고침 할 때마다 state 값이 초기화됨.
            localStorage에 저장된 값을 사용함으로써 해결하였음.

지도 : (redo, undo) 기능 비활성화, 항공뷰 -> 지도로 변경

멤버관리 : 
    로그인 - 코드를 template가 적용된 뷰에 옮겨서 적용
    회원가입 - 카카오 api를 사용해서 주소참조

아이디어) 금주의 인기매물, 그래프 활용방안 생각해보기
        매물당 점수 알고리즘(500m 이내에 버스정류장 1개당 2점, 지하철역 있으면 +10점, 편의점 한개당 1점....)
        전체 점수 : xxx점, 점수 비율은 원형 그래프로 디스플레이
        원 그래프 반경 50m 이내 편의시설 비율,,,

        검색기능으로 찾은 매물에 한해서, 지하철역 또는 편의시설까지의 접근성 정보(좋음, 보통, 낮음) 제공

        검색기능 또는 등록 매물 정렬 옵션 추가 (가격 낮은 순, 등록순, 좋아요 순, ...)

        메인화면에 이미지 fade-out방식으로 움직이는 화면 제공

        (vue Transition 활용하기)
        vue 애니메이션 효과 적용 (참고 링크):
        https://hasudoki.tistory.com/entry/Vuejs-Vue-%EC%95%A0%EB%8B%88%EB%A9%94%EC%9D%B4%EC%85%98Animate-1-Transition


## 0523...

멤버관리 : 회원가입 및 유저 프로필 수정 기능 완성

지도 : 
    카카오맵 API를 활용하여 선택 마커와 일정 거리 내에 있는 지하철 역을 새로운 마커로 표시하도록 함.
    지하철 마커 클릭 시 지하철 정보로 이동

관심 목록 이벤트처리 및 좋아요 아이콘 적용 :
---ISSUE : 
    npm에서 제공해주는 clap 에는 디폴트 상태를 지정해주는 메서드가 없어서, DB에 이미 저장된 매물을 선택 상태로 띄우는 것이 불가능했다.
    clap-btn에는 아이콘을 클릭 했을 때 clap이라는 이벤트가 실행이 되는데, 이것을 로딩과 동시에 trigger를 통해 강제로 이벤트 발생을 시켜줄 필요가 있었다.
    vue에서는 element의 trigger 발동을 위한 방법이 따로 있는데, 아래 링크 참조.
    https://stackoverflow.com/questions/31917889/is-it-possible-to-trigger-events-using-vue-js
    https://forum.vuejs.org/t/how-to-trigger-an-event-on-an-element-when-we-click-on-another-element/12050
    clap trigger 이벤트는 DOM이 mount 되기 전에는 발동 불가능, mounted 시점에서 발동해주어야했다.
    디폴트로 clap이벤트가 강제 발생할 때에는 클릭했을 때 발생하는 백엔드 처리는 따로 일어나지 말아야 하므로, clap아이콘을 div로 감싸고, 아이콘에는 clap 이벤트를, div에 click이벤트를 주어 처리하였다.


## 0524...

예정) 
카카오맵 API로 받아온 정보와 그래프/차트 연동 방법 찾아보기
유저 정보를 가지고 DB에서 관심목록을 불러와서, 지도로 검색 시 매물을 불러올 때 디폴트 값 지정하는 로직 짜기(백엔드)
회원탈퇴처리
FE CSS처리

추가로 해보면 좋을 것 - 
    1. 페이스북, 카카오, 네이버 로그인 연동? - 연동시 DB에 아이디, 이름정보는 필수로 저장되어야 함
    2. 인기매물 top10 메인화면에서 보여주기, slide 방식으로 넘기면 정보 fadeout하면서 사라지고 새로운 정보(다음정보) 나타나도록 구현
    3. 매물 추천 알고리즘 짜고, 차트와 연동

카카오맵 편의시설 마킹, 마커 주변 반경 500m 이내 편의시설 차트와 연동
메인페이지 crous



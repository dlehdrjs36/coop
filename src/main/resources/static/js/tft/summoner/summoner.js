function recordUpdate(){
    let doc = document.summonerTitle;
    doc.action = "/tft/record";
    doc.submit();
}

let checkedButtonId = "";
function changeViewRecord(recordId){
    let recordArea = document.getElementById(recordId + "Desc");
    if(checkedButtonId !== recordId) {
        if (recordArea.innerHTML !== "") {
            document.getElementById(checkedButtonId + "Desc").style.display = "none";
            recordArea.style.display = "";
            checkedButtonId = recordId;
        } else {
            if (checkedButtonId !== "") {
                document.getElementById(checkedButtonId + "Desc").style.display = "none";
            }
            recordArea.classList.add("loader");
            getGameDescData(recordId, recordArea);
            checkedButtonId = recordId;
        }
    }else{
        if(document.getElementById(checkedButtonId + "Desc").style.display === "none"){
            document.getElementById(checkedButtonId + "Desc").style.display = "";
        }else{
            document.getElementById(checkedButtonId + "Desc").style.display = "none";
        }
    }
}

function getGameDescData(matchId, recordArea) {
    const xhr = new XMLHttpRequest();
    const method = "GET";
    const url = "/tft/summoner/matchData/" + matchId;
    xhr.open(method, url);
    xhr.send();
    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            const matchData = JSON.parse(xhr.response);
            setHtmlGameDesc(matchData.matchData, recordArea);
            recordArea.classList.remove("loader");
        } else {
            console.log('failed');
        }
    }
}

function setHtmlGameDesc(matchData, recordArea) {
    let innerHtml;
    let i;
    let gameTimeMinute = Math.floor(matchData.usersData[0].timeEliminated / 60);
    let gameTimeSecond = matchData.usersData[0].timeEliminated % 60;

    innerHtml = '<table class="table text-nowrap mb-0"><thead class="table-light"><tr><th class="gameRankWidth">순위</th><th>닉네임</th><th>플레이 시간</th><th>증강체</th><th>시너지</th><th>챔피언</th></tr></thead><tbody class="bg-light-dark text-dark">';
    for(i=0; i<matchData.usersData.length; i++){
        gameTimeMinute = Math.floor(matchData.usersData[i].timeEliminated / 60);
        gameTimeSecond = matchData.usersData[i].timeEliminated % 60;
        innerHtml += matchDescHtml(matchData.usersData[i], gameTimeMinute, gameTimeSecond);
    }
    innerHtml += '</tbody></table>';
    recordArea.innerHTML = innerHtml;
}

function matchDescHtml(usersData, gameTimeMinute, gameTimeSecond){
    let innerHtml;
    let i,j;

    innerHtml = '<tr>';
    innerHtml += '<td class="align-middle"><span>' + usersData.placement + '</span></td>';
    innerHtml += '<td class="align-middle"><a class="text-dark" href="/tft/summoner/' + usersData.name + '">' + usersData.name + '</a></td>';
    innerHtml += '<td class="align-middle">';
    innerHtml += '<span>'+ gameTimeMinute + ':' +gameTimeSecond +'</span>';
    innerHtml += '</td>';

    innerHtml += '<td class="align-middle">';
    for( i=0; i<usersData.augments.length; i++){
        innerHtml += '<img class="descImg" src="/img/tft/augment/tier'+ usersData.augments[i].tier +'/' + usersData.augments[i].augment + '.png">';
    }
    innerHtml += '</td>';

    innerHtml += '<td class="align-middle">';
    innerHtml += '<div class="row align-self-md-center">';
    for( i=0; i<usersData.traits.length; i++){
        if( usersData.traits[i].style !== '0' ){
            innerHtml += '<div class="col-sm-1 descImg">';
            if( usersData.traits[i].style === '1' ){
                innerHtml += '<div class="synergyStyleBronze descImg">';
                innerHtml += '<img class="descImg" src="/img/tft/synergy/hollow/' + usersData.traits[i].name + '.png" alt="">';
                innerHtml += '</div>';
            }else if( usersData.traits[i].style === '2'){
                innerHtml += '<div class="synergyStyleSilver descImg">';
                innerHtml += '<img class="descImg" src="/img/tft/synergy/hollow/' + usersData.traits[i].name + '.png" alt="">';
                innerHtml += '</div>';
            }else if( usersData.traits[i].style === '3' ){
                innerHtml += '<div class="synergyStyleGold descImg">';
                innerHtml += '<img class="descImg" src="/img/tft/synergy/hollow/' + usersData.traits[i].name + '.png" alt="">';
                innerHtml += '</div>';
            }else{
                innerHtml += '<div class="synergyStyleChromatic descImg">';
                innerHtml += '<img class="descImg" src="/img/tft/synergy/hollow/' + usersData.traits[i].name + '.png" alt="">';
                innerHtml += '</div>';
            }
            innerHtml += '</div>';
        }
    }
    innerHtml += '</div>';
    innerHtml += '</td>';

    innerHtml += '<td class="align-middle">';
    innerHtml += '<div class="row avatar-group">';

    for(i=0; i<usersData.units.length; i++) {
        innerHtml += '<div class="col-sm-2 text-center">';
        if (usersData.units[i].tier === '1') {
            innerHtml += '<div class="tierHeight">*</div>';
        } else if (usersData.units[i].tier === '2') {
            innerHtml += '<div class="tierHeight">**</div>';
        } else {
            innerHtml += '<div class="tierHeight">***</div>';
        }
        innerHtml += '<div><img class="descImg" src="/img/tft/champion/' + usersData.units[i].characterId + '.png" alt=""></div>';
        innerHtml += '<div>';
        for( j=0; j<usersData.units[i].itemNames.length; j++ ){
            if( usersData.units[i].itemNames[j] !== '' ){
                if( usersData.units[i].itemNames[j].indexOf("Emblem") != -1 ) {
                    innerHtml += '<span><img class="itemImg" src="/img/tft/item/emblem/' + usersData.units[i].itemNames[j] + '.png" alt=""></span>';
                }else{
                    innerHtml += '<span><img class="itemImg" src="/img/tft/item/' + usersData.units[i].itemNames[j] + '.png" alt=""></span>';
                }
            }
        }
        innerHtml += '</div>';
        innerHtml += '</div>';
    }
    innerHtml += '</div></td></tr>';

    return innerHtml;
}

//소환사검색 매칭 더보기
function addMatchData(){
    let moreMatch = document.getElementById("moreMatchSpin");
    let page = parseInt(document.getElementById('page').value) + 1;
    const xhr = new XMLHttpRequest();
    const method = "GET";
    let url = "/tft/record/";
    url += page;
    url += "?puuid=" + document.getElementById('puuid').value;
    xhr.open(method, url);
    xhr.send();
    moreMatch.classList.add("loader");

    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            const matchData = JSON.parse(xhr.response);
            setMatchGame(matchData.sommonerMatchData);
            moreMatch.classList.remove("loader");
            document.getElementById('page').value = page;
        } else {
            console.log('failed');
        }
    }
}

function setMatchGame(matchData){
    let innerHtml = "";
    let i,j;
    let moreMatchButtonArea = document.getElementById("moreMatch");

    for(i=0; i<matchData.length; i++){
        const gameTimeMinute = Math.floor(matchData[i].matchDesc.timeEliminated / 60);
        const gameTimeSecond = matchData[i].matchDesc.timeEliminated % 60;

        innerHtml += '<div id="table' + matchData[i].matchDesc.metadateMatchId + '">';
        innerHtml += '<div class="row">';
        innerHtml += '<span class="col-md-3 align-self-sm-center">';
        innerHtml += '<div class="d-flex justify-content-sm-center">';
        innerHtml += '<div class="ms-1 lh-1">';
        innerHtml += '<h5 class="mb-1">';
        if(matchData[i].matchDesc.queueId === 1090) {
            innerHtml += '<a>일반 게임 (' + matchData[i].gameDatetime + ')</a>';
        }else if(matchData[i].matchDesc.queueId === 1100) {
            innerHtml += '<a>랭크 게임 (' + matchData[i].gameDatetime + ')</a>';
        }else if(matchData[i].matchDesc.queueId === 1110) {
            innerHtml += '<a>튜토리얼 (' + matchData[i].gameDatetime + ')</a>';
        }else{
            innerHtml += '<a>사용자 설정 게임 (' + matchData[i].gameDatetime + ')</a>';
        }
        innerHtml += '</h5>';
        innerHtml += '</div></div></span>';
        innerHtml += '<span class="col-md-1 align-self-sm-center"><span>' + matchData[i].matchDesc.placement + '등</span></span>';
        innerHtml += '<span class="col-md-1 align-self-sm-center"><span>'+ gameTimeMinute +':'+ gameTimeSecond +'</span></span>';
        innerHtml += '<span class="col-md-7 align-self-sm-center">';
        innerHtml += '<div class="row avatar-group">';
        for(j=0; j<matchData[i].units.length; j++) {
            innerHtml += '<div class="col-sm-1 text-center">';
            if (matchData[i].units[j].tier === '1') {
                innerHtml += '<div class="tierHeight">*</div>';
            } else if (matchData[i].units[j].tier === '2') {
                innerHtml += '<div class="tierHeight">**</div>';
            } else {
                innerHtml += '<div class="tierHeight">***</div>';
            }
            innerHtml += '<div><img class="descImg" src="/img/tft/champion/'+ matchData[i].units[j].characterId +'.png" alt=""></div>';
            innerHtml += '</div>';
        }
        innerHtml += '</div></span>';
        // innerHtml += '<span class="col-md-1 align-self-sm-center text-dark">';
        // innerHtml += '<button class="btn btn-white" id="' + matchData[i].matchDesc.metadateMatchId + '" onclick="changeViewRecord(this.id)">상세</button>';
        // innerHtml += '</span>';
        innerHtml += '</div>';
        innerHtml += '<div id="'+ matchData[i].matchDesc.metadateMatchId +'Desc"></div></div>';
    }
    document.getElementById("matchTable").innerHTML += innerHtml;

    if(matchData.length < 10){
        moreMatchButtonArea.style.display = "none";
    }
}
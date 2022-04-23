const loader = document.getElementById("loader");

function recordUpdate(){
    let doc = document.summonerTitle;
    doc.action = "/tft/record";
    doc.submit();
}

let checkedButtonId;
function changeViewRecord(recordId){
    let recordArea = document.getElementById(recordId + "Desc");
    if(recordArea !== null){
        document.getElementById(checkedButtonId + "Desc").style.display = "none";
        recordArea.style.display = "";
        checkedButtonId = recordId;
    }else{
        document.getElementById(checkedButtonId + "Desc").style.display = "none";
        getGameDescData(recordId);
        checkedButtonId = recordId;
    }
}

function getGameDescData(matchId) {
    document.getElementById("loader").style.display = "";
    const xhr = new XMLHttpRequest();
    const method = "GET";
    const url = "/tft/summoner/matchData/" + matchId;
    xhr.open(method, url);
    xhr.send();
    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            const matchData = JSON.parse(xhr.response);
            setHtmlGameDesc(matchData.matchData);
            document.getElementById("loader").style.display = "none";
        } else {
            console.log('failed');
        }
    }
}

function setHtmlGameDesc(matchData) {
    let innerHtml;
    let userName = document.getElementById('name').value;
    let i;
    let gameTimeMinute = Math.floor(matchData.gameLength / 60);
    let gameTimeSecond = matchData.gameLength % 60;

    innerHtml = '<div class="contentsPadding border border-dark border-5" id="'+ matchData.matchId + 'Desc" style=""><div class="row"><div>';
    for( i=0; i<matchData.usersData.length; i++ ){
        if(matchData.usersData[i].name === userName){
            innerHtml += matchDescHtml(matchData.usersData[i], gameTimeMinute, gameTimeSecond);
        }
    }
    innerHtml += '</div></div></div>';
    innerHtml += '<div class="row contentsMargin border-top border-start border-end border-dark border-5">';
    for( i=0; i<matchData.usersData.length; i++ ){

        innerHtml += '<div class="border-bottom border-dark border-5">';
        innerHtml += '<div>';
        innerHtml += matchDescHtml(matchData.usersData[i], gameTimeMinute, gameTimeSecond);
        innerHtml += '</div></div></div>';
    }
    innerHtml += '</div>';
    document.getElementById('matchDescArea').innerHTML += innerHtml;
}

function matchDescHtml(usersData, gameTimeMinute, gameTimeSecond){
    let innerHtml;
    let j,k;

    innerHtml = '<div class="contentsPadding bg-primary bg-opacity-75 row text-white">';
    innerHtml += '<div class="col-sm-2 contentsBox text-center align-self-md-center">';
    innerHtml += '<div>';
    innerHtml += usersData.name;
    innerHtml += '</div>';
    innerHtml += '<div>';
    innerHtml += usersData.placement + '등';
    innerHtml += '</div>';
    innerHtml += '<div>';
    innerHtml += '<span>'+gameTimeMinute+'</span>';
    innerHtml += ':';
    innerHtml += '<span>'+gameTimeSecond+'</span>';
    innerHtml += '</div>';
    innerHtml += '</div>';
    innerHtml += '<div class="col-sm-1 contentsBox text-center align-self-md-center">';
    for( j=0; j<usersData.augments.length; j++){
        innerHtml += '<img class="descImg" src="/img/tft/augment/tier'+ usersData.augments[j].tier +'/' + usersData.augments[j].augment + '.png">';
    }
    innerHtml += '</div>';
    innerHtml += '<div class="row col-sm-2 contentsBox text-center align-self-md-center">';
    for( j=0; j<usersData.traits.length; j++){
        if( usersData.traits[j].style !== '0' ){
            innerHtml += '<div class="col-sm-6 descImg">';
            if( usersData.traits[j].style === '1' ){
                innerHtml += '<div class="synergyStyleBronze descImg">';
                innerHtml += '<img class="descImg" src="/img/tft/synergy/hollow/' + usersData.traits[j].name + '.png" alt="">';
                innerHtml += '</div>';
            }else if( usersData.traits[j].style === '2'){
                innerHtml += '<div class="synergyStyleSilver descImg">';
                innerHtml += '<img class="descImg" src="/img/tft/synergy/hollow/' + usersData.traits[j].name + '.png" alt="">';
                innerHtml += '</div>';
            }else if( usersData.traits[j].style === '3' ){
                innerHtml += '<div class="synergyStyleGold descImg">';
                innerHtml += '<img class="descImg" src="/img/tft/synergy/hollow/' + usersData.traits[j].name + '.png" alt="">';
                innerHtml += '</div>';
            }else{
                innerHtml += '<div class="synergyStyleChromatic descImg">';
                innerHtml += '<img class="descImg" src="/img/tft/synergy/hollow/' + usersData.traits[j].name + '.png" alt="">';
                innerHtml += '</div>';
            }
            innerHtml += '</div>';
        }
    }
    innerHtml += '</div>';
    innerHtml += '<div class="row col-sm-7">';
    for( j=0; j<usersData.units.length; j++){
        innerHtml += '<div class="col-sm-2 text-center">';

        if( usersData.units[j].tier === '1' ){
            innerHtml += '<div class="tierHeight">*</div>';
        }else if( usersData.units[j].tier === '2' ){
            innerHtml += '<div class="tierHeight">**</div>';
        }else{
            innerHtml += '<div class="tierHeight">***</div>';
        }
        innerHtml += '<div><img class="descImg" src="/img/tft/champion/' + usersData.units[j].characterId + '.png" alt=""></div>';
        innerHtml += '<div>';
        for( k=0; k<usersData.units[j].itemNames.length; k++ ){
            if( usersData.units[j].itemNames[k] !== '' ){
                if( usersData.units[j].itemNames[k].indexOf("Emblem") != -1 ) {
                    innerHtml += '<span><img class="itemImg" src="/img/tft/item/emblem/' + usersData.units[j].itemNames[k] + '.png" alt=""></span>';
                }else{
                    innerHtml += '<span><img class="itemImg" src="/img/tft/item/' + usersData.units[j].itemNames[k] + '.png" alt=""></span>';
                }
            }
        }
        innerHtml += '</div>';
        innerHtml += '</div>';
    }
    innerHtml += '</div>';

    return innerHtml;
}

function init(){
    if(document.getElementById("tftRecordArea") != null) {
        let firstGameRecordId = document.getElementById("initMatchId").value;
        let firstRecordButton = document.getElementById(firstGameRecordId);
        firstRecordButton.checked = true;
        checkedButtonId = firstGameRecordId;
    }
    document.getElementById("loader").style.display = "none";
}
init();
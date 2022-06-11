const championNameList = ["Ahri","Alistar","Ashe","Blitzcrank","Brand","Braum","Caitlyn","Camille","ChoGath","Corki","Darius","Draven","Ekko","Ezreal","Galio","Gangplank","Gnar","Illaoi","Irelia","JarvanIV","Jayce","Jhin","Jinx","Kaisa","Kassadin","KhaZix","Leona","Lucian","Lulu","Malzahar","MissFortune","Morgana","Nocturne","Orianna","Poppy","Quinn","RekSai","Renata","Sejuani","Senna","Seraphine","Silco","Singed","Sivir","Swain","Syndra","TahmKench","Talon","Tryndamere","Twitch","Veigar","Vex","_Vi","Viktor","Warwick","Zac","Zeri","Ziggs","Zilean","Zyra"];
const championNameKrList = ["아리","알리스타","애쉬","블리츠크랭크","브랜드","브라움","케이틀린","카밀","초가스","코르키","다리우스","드레이븐","에코","이즈리얼","갈리오","갱플랭크","나르","일라오이","이렐리아","자르반4세","제이스","진","징크스","카이사","카사딘","카직스","레오나","루시안","룰루","말자하","미스포츈","모르가나","녹턴","오리아나","뽀삐","퀸","렉사이","레나타글라스크","세주아니","세나","세라핀","실코","신지드","시비르","스웨인","신드라","탐켄치","탈론","트린다미어","트위치","베이가","벡스","바이","빅토르","워윅","자크","제리","직스","질리언","자이라"];
const searchChampionName = document.getElementById("searchChampionName");
const keywords = document.querySelector(".championKeywords");
let keywordsFlg = false;

function searchChampion(e){
    let search = e.target.value;
    let keywords = document.querySelector(".championKeywords");

    if(keywords.innerHTML != "") {
        while ( keywords.hasChildNodes() ) { keywords.removeChild( keywords.firstChild ); }
    }

    if(search === "") {
        document.querySelector(".championKeywordArea").classList.add("hide");
    } else {
        document.querySelector(".championKeywordArea").classList.remove("hide");
    }

    if( search !== "") {
        for(let i = 0; i < championNameKrList.length; i++){
            if(championNameKrList[i].indexOf(search) !== -1){
                const innerHtml = '<div class="championKeyword" name="' + championNameList[i] + '" onclick=insertSearchKeyword("' + i + '")>' + championNameKrList[i] + '</div>';
                keywords.innerHTML += innerHtml;
            }
        }
    }
}

function insertSearchKeyword( keyword ){
    if(keyword.type !== "") {
        searchChampionName.value = championNameKrList[keyword];
    }
    document.querySelector(".championKeywordArea").classList.add("hide");
}

function championSearch(){
    let doc = document.searchChampion;
    let searchChampionName = doc.searchChampionName.value;
    for(let i=0; i<championNameKrList.length; i++){
        if(searchChampionName === championNameKrList[i]){
            doc.action = "/tft/championList/" + championNameList[i];
            doc.submit();
            return "";
        }
    }
    doc.action = "/tft/championList";
    doc.submit();
}

function summonerSearch(){
    let doc = document.searchSummoner;
    let searchSummonerName = doc.searchSummonerName.value;
    if( searchSummonerName != "" ) {
        doc.action = "/tft/summoner/" + searchSummonerName;
        doc.submit();
    }
}

function indexSummonerSearch(){
    let doc = document.indexSearchSummoner;
    let searchSummonerName = doc.indexSearchSummonerName.value;
    if( searchSummonerName != "" ) {
        doc.action = "/tft/summoner/" + searchSummonerName;
        doc.submit();
    }
}

if(searchChampionName != null) {
    searchChampionName.addEventListener('input', searchChampion);
    searchChampionName.addEventListener('focus', searchChampion);
    searchChampionName.addEventListener('focusout', function (){
        if(!keywordsFlg){
            document.querySelector(".championKeywordArea").classList.add("hide");
        }
    });
}

if(keywords != null) {
    keywords.addEventListener('mouseenter', function (){
        keywordsFlg = true;
    });
    keywords.addEventListener('mouseleave', function (){
        keywordsFlg = false;
    });
}

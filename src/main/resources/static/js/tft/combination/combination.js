const champions = ["Nocturne|600 / 1080 / 1944|40 / 80|45 / 81 / 145.8|25 / 25|0.7|1|말할 수 없는 공포|녹턴이 대상을 공포에 질리게 해 잠시 동안 기절시키고, 기절 지속시간 동안 마법피해를 입힙니다.$$피해량: 190 / 300 / 400$기절 지속시간: 2 / 2.5 / 3|Hextech$Assassin","Darius|650 / 1170 / 2106|70 / 120|60 / 108 / 194.4|40 / 40|0.5|1|학살|다리우스가 도끼를 휘둘러 마법 피해를 입히고 도끼에 맞은 적 유닛당 체력을 회복합니다.$$피해량: 200 / 275 / 350$회복: 120 / 140 / 160|Syndicate$Bodyguard","Brand|500 / 900 / 1620|0 / 20|40 / 72 / 129.6|20 / 20|0.65|3|불태우기|브랜드가 가장 가까운 적에게 불덩이를 발사해 마법 피해를 입히고 4초간 불태웁니다. 대상이 이미 불타는 상태라면 추가 마법 피해를 입고 잠시 동안 기절시킵니다.$$VIP 추가 효과: 브랜드가 두 번째 불덩이를 다른 근처 대상에게 발사해 (불타는 적 우선) 30% 감소한 피해를 입힙니다.$$피해량: 135 / 175 / 235$추가 피해량: 165 / 225 / 300$기절 지속 시간: 1 / 1.5 / 2|Debonair$Arcanist","Poppy|650 / 1170 / 2106|50 / 100|60 / 108 / 194.4|40 / 40|0.55|1|방패 던지기|뽀삐가 가장 멀리 있는 적에게 방패를 던져 방어력에 비례하는 마법 피해를 입힙니다. 방패는 다시 돌아와 뽀삐에게 피해를 흡수하는 보호막을 부여합니다.$$방어력 비례 피해: 180% / 210% / 240%$보호막 흡수량: 225 / 275 / 325|Yordle$Bodyguard","Singed|650 / 1170 / 2106|100 / 150|60 / 108 / 194.4|40 / 40|0.55|1|던져넘기기|신지드가 근처의 적을 가장 큰 적 무리에게 던져 수 초 동안 기절시킵니다. 인접한 모든 적은 마법 피해를 입고 잠시 기절합니다.$$피해량: 125 / 175 / 250$기절 지속시간: 1.5 / 2 / 3|Chemtech$Innovator","Ezreal|500 / 900 / 1620|0 / 40|45 / 81 / 145.8|15 / 15|0.65|4|신비한 화살|이즈리얼이 대상에게 미사일을 발사하여 물리 피해(공격력 의 150%+추가 피해량)를 입힙니다. 만약 미사일이 적중하면 중첩 가능한 추가 공격 속도를 20%만큼 획득하며 5중첩에서 최대 공격 속도를 얻습니다.$$추가 피해량: 25 / 50 / 100|Scrap$Innovator","Illaoi|700 / 1260 / 2268|40 / 80|60 / 108 / 194.4|40 / 40|0.55|1|혹독한 가르침|일라오이가 대상을 내리쳐 5초 동안 자신의 영혼과 연결하고 마법 피해를 입힙니다. 연결 상태에서 일라오이는 대상이 받는 피해의 일부만큼 체력을 회복합니다.$$마법 피해: 200 / 325 / 550$입히는 피해에 따른 체력 회복량: 25% / 30% / 40%|Mercenary$Bruiser","JarvanIV|700 / 1260 / 2268|50 / 100|55 / 99 / 178.2|40 / 40|0.6|1|영겁의 깃발|자르반 4세가 근처에 깃발을 던져 5초 동안 3칸 내 모든 아군의 공격 속도를 상승시킵니다.$$추가 공격 속도: 40% / 50% / 70%|Hextech$Striker","Ziggs|500 / 900 / 1620|0 / 60|55 / 99 / 178.2|15 / 15|0.6|4|소형 화염 폭탄|직스가 대상에게 폭탄을 던집니다. 잠시 후 폭탄이 떨어져 중심에 있는 적에게 마법 피해를 입히고 인접한 적에게는 절반의 피해를 입힙니다.$$피해량: 300 / 400 / 550|Scrap$Yordle$Arcanist","Camille|700 / 1260 / 2268|0 / 60|45 / 81 / 145.8|40 / 40|0.6|1|방어적 휩쓸기|카밀이 4초 동안 피해를 흡수하는 보호막을 얻습니다. 그리고 다리를 휩쓸어 원뿔 범위에 있는 적들에게 마법 피해를 입힙니다. 보호막이 유지되는 동안 카밀이 공격 시 체력을 회복합니다.$$피해량: 150 / 200 / 300$보호막 흡수량: 225 / 300 / 435$공격당 체력 회복: 30 / 50 / 80|Clockwork$Challenger","Kassadin|750 / 1350 / 2430|60 / 100|50 / 90 / 162|40 / 40|0.65|1|무의 구체|카사딘이 대상에게 공허 구체를 발사하여 마법 피해를 입힙니다. 구체에 적중당한 적들은 다음 스킬 사용에 필요한 마나 비용이 50% 증가합니다. 또한 5초 동안 카사딘이 피해를 흡수하는 보호막을 얻고 받는 피해가 25% 감소합니다.$$피해량: 250 / 325 / 400$보호막 흡수량: 150 / 250 / 350|Mutant$Scholar","Caitlyn|500 / 900 / 1620|0 / 110|45 / 81 / 145.8|15 / 15|0.8|4|비장의 한 발|케이틀린이 가장 멀리 있는 적을 향해 강력한 총알을 발사합니다. 총알은 처음 적중한 적에게 마법 피해를 입힙니다.$$피해량: 800 / 1400 / 2000|Enforcer$Sniper","Twitch|450 / 810 / 1458|0 / 35|45 / 81 / 145.8|15 / 15|0.7|3|관통 화살|트위치가 적을 관통하는 강력한 화살을 대상에게 발사하여 물리 피해(공격력  %+추가 피해)를 입히고 5초 동안 대상의 회복 효과가 50% 감소합니다.$$공격력: 125% / 130% / 140%$추가 피해량: 30 / 50 / 70|Chemtech$Assassin","RekSai|750 / 1350 / 2430|60 / 90|55 / 99 / 178.2|45 / 45|0.7|1|성난 이빨|렉사이가 대상을 깨물어 물리 피해(공격력의 125%+추가 피해)를 입히고 체력을 회복합니다. 이미 깨물었던 대상일 경우 더 많은 체력을 회복합니다.$$피해량: 100 / 150 / 200$기본 회복량: 150 / 250 / 400$추가 체력 회복: 250 / 350 / 500|Mutant$Bruiser$Striker","Lulu|600 / 1080 / 1944|60 / 125|40 / 72 / 129.6|25 / 25|0.65|3|급성장|룰루가 체력이 낮은 아군을 커지게 해 추가 체력을 부여하고 주변 적을 공중으로 띄워 올립니다. 아군이 이미 커진 상태라면 체력을 회복합니다.$$추가 체력: 350 / 370 / 390$대상 수: 1 / 2 / 3|Yordle$Enchanter","Blitzcrank|650 / 1170 / 2106|175 / 175|65 / 117 / 210.6|45 / 45|0.5|1|로켓 손|블리츠크랭크가 가장 멀리 있는 적을 당겨 마법 피해를 입히고 1.5초 동안 기절시킵니다. 다음 공격은 적을 1초 동안 공중으로 띄워 올립니다. 아군은 블리츠크랭크가 당긴 적이 사거리 안에 있을 경우 우선적으로 공격합니다.$$피해량: 150 / 300 / 900|Scrap$Bodyguard","Sejuani|750 / 1350 / 2430|20 / 80|55 / 99 / 178.2|45 / 45|0.55|1|혹한의 분노|세주아니가 브리슬에게 돌격 명령을 내려 대상에게 마법 피해를 입히고 잠시 동안 기절 시킵니다. 이후 세주아니는 서리 갑옷 효과를 얻어 4초 동안 방어력과 마법 저항력이 증가합니다.$$방어력과 마법 저항력: 50 / 75 / 125$기절 지속시간: 1.5 / 2 / 3$마법 피해: 275 / 400 / 650|Hextech$Enforcer$Bruiser","Swain|800 / 1440 / 2592|40 / 85|40 / 72 / 129.6|45 / 45|0.6|2|죽음의 손길|스웨인이 대상을 향해 원뿔 범위로 관통하는 섬뜩한 힘을 발사하여 범위 내 적에게 마법 피해를 입히고 적중한 적 하나당 체력을 회복합니다.$$피해량: 250 / 350 / 500$회복량: 250 / 300 / 350|Hextech$Arcanist","Syndra|600 / 1080 / 1944|50 / 100|40 / 72 / 129.6|20 / 20|0.65|4|의지의 힘|신드라가 가장 가까운 적을 가장 멀리 있는 적에게 던집니다. 던져진 적은 충돌 시 1칸 내 모든 적에게 마법 피해를 입히고 잠시 동안 기절합니다.$$VIP 추가 효과: 의지의 힘 스킬의 피해 범위가 1칸 넓어지고 영향을 받은 적들을 1.5초 동안 공중에 띄웁니다.$$피해량: 225 / 325 / 500$기절 지속시간: 2 / 2.5 / 3|Debonair$Scholar","Ashe|600 / 1080 / 1944|60 / 125|70 / 126 / 226.8|20 / 20|0.7|3|일제 사격|애쉬가 대상을 중심으로 화살 8개를 일제히 쏴서 각각 물리 피해(공격력 의 100%)를 입힙니다. 화살을 맞은 적은 3초간 공격 속도가 15% 감소합니다.$$공격력 %: 100% / 100% / 100%|Syndicate$Sniper","Warwick|750 / 1350 / 2430|-|45 / 81 / 145.8|40 / 40|0.8|1|끝없는 허기|기본 지속 효과: 워윅의 공격이 대상 현재 체력에 비례하는 추가 마법 피해를 입히고 워윅이 체력을 회복합니다.$$대상 현재 체력 비례 피해량: 7% / 9% / 12%$회복량: 35 / 45 / 65|Chemtech$Challenger","Zyra|600 / 1080 / 1944|60 / 120|40 / 72 / 129.6|20 / 20|0.7|4|휘감는 가시|자이라가 적이 가장 많은 줄에 덩굴을 소환해 마법 피해를 입히고 수 초 동안 기절시킵니다.$$피해량: 325 / 450 / 675$기절 지속시간: 1.5 / 2 / 2.5|Syndicate$Scholar","Zilean|600 / 1080 / 1944|40 / 80|40 / 72 / 129.6|20 / 20|0.6|4|시한 폭탄|질리언이 가장 가까운 적에게 폭탄을 설치해 수 초 동안 기절시킵니다. 기절 지속시간이 끝나거나 대상이 처치되면 폭탄이 폭발하여 인접한 적에게 마법 피해를 입히고 4초 동안 적의 공격 속도가 감소합니다.$$피해량: 250 / 350 / 700$공격 속도 감소량: 25% / 35% / 50%$기절 지속시간: 1.5 / 2 / 2.5|Clockwork$Innovator","Corki|650 / 1170 / 2106|25 / 50|55 / 99 / 178.2|25 / 25|0.7|4|폭격|코르키가 대상에게 미사일을 발사합니다. 미사일은 충돌 시 폭발해 1칸 내 적들에게 마법 피해를 입힙니다.$$피해량: 200 / 260 / 333|Yordle$Twinshot","Quinn|550 / 990 / 1782|70 / 140|55 / 99 / 178.2|20 / 20|0.7|4|무장 해제 공격|퀸이 가장 빠른 공격 속도를 가진 대상에게 발러를 보내 대상과 주변 적들에게 마법 피해를 입히고 수 초 동안 무장 해제시킵니다.$$피해량: 200 / 300 / 650$무장 해제 지속시간: 1.5 / 2 / 2.5|Mercenary$Challenger","Talon|650 / 1170 / 2106|-|45 / 81 / 145.8|25 / 25|0.7|1|검의 최후|기본 지속 효과: 탈론의 첫 번째 공격이 출혈을 일으켜 7초 동안 마법 피해를 입힙니다. 매 3번째 공격이 대상에게 추가 출혈을 적용합니다.$$VIP 추가 효과: 탈론의 출혈 효과가 고정 피해를 입히고 100% 더 오래 지속됩니다.$$피해량: 450 / 650 / 950|Debonair$Assassin","Gangplank|750 / 1350 / 2430|0 / 50|65 / 117 / 210.6|40 / 40|0.75|1|혀어어어업상|갱플랭크가 총으로 대상을 공격해 물리 피해(공격력 의 170%+추가 피해량)를 입힙니다. 이 공격으로 챔피언을 처치하면 갱플랭크가 1골드를 약탈합니다.$$추가 피해량: 120 / 160 / 225|Mercenary$Twinshot","Gnar|700 / 1260 / 2268|0 / 80|60 / 108 / 194.4|40 / 40|0.7|3|나르!|나르가 메가 나르로 변신하고, 전투가 끝날 때까지 메가 나르 상태가 유지됩니다. 메가 나르는 3칸 내에서 가장 먼 적에게 돌덩이를 던져, 경로에 있는 모든 적에게 물리 피해(공격력 의 185%+기본 피해량)를 입힙니다.$$메가 나르 상태에서는 근접 공격을 하며, 체력이 증가롤아이콘-주문력 신규하고 마나 소모량이 20 감소합니다.$$변신 시 체력: 500 / 750 / 1200$기본 피해량: 150 / 200 / 300|Socialite$Yordle$Striker","Leona|850 / 1530 / 2754|75 / 125|50 / 90 / 162|50 / 50|0.6|1|일식|레오나가 빛의 힘을 불러내어 잠시 동안 피해를 흡수하는 보호막을 얻습니다. 레오나와 2칸 내의 아군들은 같은 시간 동안 방어력 및 마법 저항력을 얻습니다.$$VIP 추가 효과: 공격 대상이 레오나인 유닛 하나당 레오나가 매초 최대 체력의 0.8%만큼 체력을 회복합니다.$$보호막 흡수량: 400 / 650 / 1000$지속시간: 5 / 5 / 8$방어력과 마법 저항력: 30 / 50 / 80|Debonair$Bodyguard","Lucian|700 / 1260 / 2268|0 / 40|55 / 99 / 178.2|30 / 30|0.75|3|끈질긴 추격|루시안이 현재 대상에서 먼 곳으로 돌진하며 주변 적에게 총알을 수 발 발사해 각각 마법 피해를 입힙니다.$$피해량: 185 / 295 / 315$총알 수: 2 / 2 / 3|Hextech$Twinshot","Malzahar|650 / 1170 / 2106|30 / 80|40 / 72 / 129.6|30 / 30|0.7|4|재앙의 환상|말자하가 영향을 받지 않은 가장 가까운 대상의 정신을 오염시켜 8초 동안 마법 피해를 입히고 적의 마법 저항력을 40%만큼 감소시킵니다.$$대상이 사망하면 남은 시간 동안 재앙의 환상이 영향을 받지 않은 가장 가까운 대상에게 퍼집니다.$$피해량: 650 / 900 / 1025$대상 전염: 1 / 1 / 2|Mutant$Arcanist","Morgana|800 / 1440 / 2592|60 / 120|50 / 90 / 162|50 / 50|0.6|2|영혼의 족쇄|모르가나가 3초 동안 피해를 흡수하는 보호막을 얻고, 2칸 내 모든 적을 자신과 묶어 보호막이 사라질 때까지 매초 마법 피해를 입힙니다.$$보호막이 파괴되지 않고 사라지면 모든 대상을 수 초 동안 기절시킵니다. 보호막이 파괴되면 30의 마나를 돌려받습니다.$$피해량: 80 / 125 / 175$기절 지속시간: 1.5 / 2 / 2.5$보호막 흡수량: 475 / 575 / 675|Syndicate$Enchanter","MissFortune|650 / 1170 / 2106|40 / 80|55 / 99 / 178.2|25 / 25|0.7|3|총알은 비를 타고|미스 포츈이 대상 주변에 4회 총알을 퍼부어 해당 지역에 있는 적에게 마법 피해를 입힙니다. 적중당한 적은 6초간 받는 체력 회복 효과가 50% 감소합니다.$$마법 피해: 275 / 375 / 550|Mercenary$Sniper","Vex|850 / 1530 / 2754|40 / 80|45 / 81 / 145.8|50 / 50|0.6|2|거리 두기|벡스가 4초 동안 피해를 흡수하는 보호막을 얻습니다. 보호막이 사라지면 2칸 내의 모든 적에게 마법 피해를 입히고 보호막 지속 시간 동안 보호막이 파괴되지 않았다면 추가 피해를 입힙니다.$$보호막이 파괴되었다면 이번 전투에서 거리 두기가 15% 강력해집니다. 이 효과는 중첩될 수 있습니다.$$보호막 흡수량: 550 / 700 / 900$기본 피해량: 100 / 135 / 175$추가 피해량: 100 / 135 / 175|Yordle$Arcanist","Senna|650 / 1170 / 2106|30 / 80|60 / 108 / 194.4|25 / 25|0.75|4|꿰뚫는 어둠|세나가 대상 방향으로 광선을 발사해 물리 피해(공격력 의 150%+피해량)를 입힙니다. 적중한 적 하나당 체력이 가장 낮은 아군의 체력이 피해량의 50%만큼 회복됩니다.$$피해량: 80 / 120 / 180|Socialite$Enchanter","Ekko|700 / 1260 / 2268|60 / 120|55 / 99 / 178.2|40 / 40|0.7|1|평행 시간 교차|에코가 가장 큰 적 무리에게 장치를 날리는 잔상을 남깁니다. 장치 착지 시 범위 내의 적들은 마법 피해를 입고 4초 동안 공격 속도가 감소합니다. 범위 내의 아군은 4초 동안 공격 속도가 증가합니다.$$피해량: 150 / 200 / 350$공격 속도 둔화: 25% / 25% / 35%$추가 공격 속도: 35% / 40% / 50%|Scrap$Assassin$Innovator","Zac|800 / 1440 / 2592|60 / 100|60 / 108 / 194.4|45 / 45|0.6|1|타아앙!|자크가 팔을 최대 3칸까지 뻗어 가장 멀리 떨어진 2명의 적을 앞으로 당기고 마법 피해를 입힙니다. 이 동안 자크가 입는 피해가 50% 감소합니다.$$피해량: 300 / 400 / 600|Chemtech$Bruiser","ChoGath|700 / 1260 / 2268|100 / 165|90 / 162 / 291.6|50 / 50|0.55|1|포식|초가스가 범위 내 가장 체력이 낮은 적을 집어삼켜 마법 피해를 입힙니다. 만약 대상을 처치하면 초가스가 포식 중첩을 하나 (초고속 모드에서는 2개) 획득하며, 최대 횟수까지 중첩됩니다. 포식 중첩 하나당 영구적으로 몸집이 커지며 2%의 추가 체력을 영구적으로 얻습니다.$$피해량: 850 / 900 / 1050$최대 포식 중첩: 20 / 40 / 999|Mutant$Colossus$Bruiser","Tryndamere|750 / 1350 / 2430|40 / 100|70 / 126 / 226.8|35 / 35|0.75|1|회전 베기|트린다미어가 적이 가장 많은 곳을 향해 일직선상으로 회전하며 경로에 있는 적에게 물리 피해(공격력의 100%+추가 피해량)를 입힙니다. 또한 다음 3번의 기본 공격이 강화되어 20%의 추가 피해를 입힙니다.$$추가 피해량: 50 / 75 / 100|Chemtech$Challenger","Draven|800 / 1440 / 2592|0 / 40|70 / 126 / 226.8|30 / 30|0.8|4|회전 도끼|기본 지속 효과: 드레이븐이 대상 방어력의 25%를 무시합니다.$$드레이븐이 회전 도끼를 사용하며 다음 공격 시 추가 물리 피해(공격력 롤아이콘-공격력 신규의 %+피해량롤아이콘-주문력 신규)를 입힙니다. 도끼는 대상에게 적중한 후 드레이븐의 원래 위치로 돌아옵니다. 돌아오는 도끼를 잡으면 도끼를 다시 강화합니다. 드레이븐은 한 번에 최대 2개의 회전 도끼를 사용할 수 있습니다.$$VIP 추가 효과: 드레이븐의 기본 공격 사거리가 무제한이 되고 추가로 대상 방어력의 25%를 무시합니다.$$공격력 %: 170% / 180% / 400%$피해량: 120 / 150 / 400|Debonair$Challenger","Renata|800 / 1440 / 2592|0 / 60|40 / 72 / 129.6|30 / 30|0.7|3|맹독 파도|레나타가 6칸 내 가장 큰 적 무리에게 맹독 파도를 내보내 경로에 있는 모든 적을 15초간 중독시킵니다. 중독된 적은 공격 속도가 15% 감소하고 매초 마법 피해를 입습니다. 독 피해는 중첩될 수 있지만, 레나타가 죽으면 사라집니다.$$초당 피해량: 40 / 65 / 220|Chemtech$Scholar","_Vi|900 / 1620 / 2916|0 / 40|70 / 126 / 226.8|50 / 50|0.7|1|필트오버 분쇄 건틀릿|바이가 피해를 흡수하는 보호막을 얻고, 대상과 그 뒤에 있는 적들에게 마법 피해를 입힙니다.$$스킬을 두 번째로 사용하면 대상을 뚫고 돌진합니다.$$스킬을 세 번째로 사용하면 대상을 공중으로 띄웠다가 다시 땅으로 꽂아 대상 주변 원 안에 있는 적들에게 피해롤아이콘-주문력 신규를 입힙니다.$$보호막 흡수량: 225 / 325 / 750$피해량: 125 / 200 / 450$파동 피해량: 250 / 350 / 900|Rivals$Enforcer$Bruiser","Braum|1100 / 1980 / 3564|120 / 200|70 / 126 / 226.8|60 / 60|0.6|1|금고 부수기|브라움이 금고 문을 지면에 내리쳐 대상을 향해 균열을 일으킵니다. 브라움 주변 2칸 내에 있는 적들과 균열에 적중당한 적들은 수 초 동안 기절하고 마법 피해를 입습니다.$$피해량: 100 / 200 / 600$기절 지속시간: 2 / 2.75 / 8|Syndicate$Bodyguard","Seraphine|750 / 1350 / 2430|80 / 150|40 / 72 / 129.6|30 / 30|0.7|4|앙코르|세라핀이 가장 큰 적 유닛 무리를 향해 노래를 불러 적들에게 마법 피해를 입힙니다. 노래에 적중된 아군은 체력을 회복하고 4초 동안 공격 속도가 증가합니다.$$피해량: 250 / 400 / 1200$회복량: 250 / 350 / 1000$추가 공격 속도: 30% / 45% / 100%|Socialite$Innovator","Sivir|700 / 1260 / 2268|0 / 60|60 / 108 / 194.4|30 / 30|0.75|4|튕기는 부메랑|5초 동안 시비르의 공격 속도가 증가하고 기본 공격이 수 회 튕기며 적중한 적에게 물리 피해(공격력 의 33%)를 입힙니다.$$튕김: 4 / 5 / 9$추가 공격 속도: 60% / 80% / 250%|Hextech$Striker","Ahri|800 / 1440 / 2592|30 / 50|45 / 81 / 145.8|30 / 30|0.75|4|현혹의 구슬|아리가 일직선으로 5칸에 구슬을 던졌다 받습니다. 구슬은 닿는 모든 적에게 마법 피해를 입힙니다. 한 구슬에 맞은 적은 두 번째 구슬부터 80%의 피해를 받습니다. 이번 전투에 아리가 이 스킬을 사용한 횟수당 구슬을 추가로 던집니다.$$피해량: 125 / 190 / 450$추가 구슬: 1 / 1 / 2|Syndicate$Arcanist","Alistar|1400 / 2520 / 4536|85 / 170|90 / 162 / 291.6|80 / 80|0.6|1|분쇄|알리스타가 대상에게 돌진해 짧은 거리만큼 뒤로 밀어냅니다. 이후 땅을 내리쳐 마법 피해를 입히고 주변 모든 적을 잠시 동안 기절시킵니다.$$피해량: 150 / 250 / 1000$기절 지속시간: 2 / 2.5 / 8|Hextech$Colossus","Orianna|750 / 1350 / 2430|50 / 130|50 / 90 / 162|30 / 30|0.75|4|명령: 충격파|오리아나가 가장 큰 챔피언 무리에 구체를 보내 충격파 발산 명령을 내립니다. 2칸 내의 아군은 4초 동안 보호막을 얻고 같은 범위 내의 적은 잠시 공중에 띄워져 마법 피해를 입습니다. 구체와 인접한 적은 끌려들어가 잠시 동안 기절합니다.$$보호막 흡수량: 120 / 170 / 600$피해량: 300 / 450 / 1200$기절 지속시간: 1 / 1 / 4|Clockwork$Enchanter","Irelia|900 / 1620 / 291.6|0 / 40|70 / 126 / 210.6|50 / 50|0.8|1|칼날 쇄도|이렐리아가 대상에게 돌진해 물리 피해(공격력의 185%+추가 피해)를 입힙니다. 만약 대상이 처치되면 체력이 가장 낮은 적에게 칼날 쇄도를 다시 사용합니다.$$피해량: 60 / 90 / 550|Scrap$Striker","Jhin|700 / 1260 / 2268|0 / 70|85 / 153 / 275.4|30 / 30|0.9|4|커튼 콜|진이 다음 4번의 사격 동안 자신의 무기를 강력한 저격소총으로 바꿉니다. 각 총탄은 공격력에 비례하는 물리 피해를 입히고 관통한 대상마다 33% 감소합니다. 4번째 총탄은 항상 치명타가 적용되며 대상이 잃은 체력에 비례하여 100%의 추가 피해를 입힙니다.$$기본 지속 효과: 진이 항상 같은 공격 속도로 공격합니다. 진의 추가 공격 속도 1%는 0.8의 공격력으로 전환됩니다.$$공격력 %: 175% / 200% / 300%$초당 공격 횟수: 0.9 / 0.9 / 1.4|Clockwork$Sniper","KhaZix|900 / 1620 / 2916|0 / 40|85 / 153 / 275.4|35 / 35|0.9|1|공허의 습격|카직스가 체력이 가장 낮은 적에게 도약해 물리 피해(%+추가 피해)를 입힙니다. 대상은 다음 스킬을 사용할 때까지 최대 마나가 50% 증가합니다.$$공격력 %: 195% / 200% / 225%$추가 피해량: 175 / 225 / 500|Mutant$Assassin","Galio|1200 / 2160 / 3888|200 / 300|110 / 198 / 356.4|70 / 70|0.65|1|거신 출현|갈리오가 무적 상태가 되어 하늘로 도약하고 가장 큰 적 무리를 강타합니다. 넓은 범위 내의 적들은 마법 피해(피해량 +갈리오 최대 체력의 8%)를 입고 수 초간 공중에 뜹니다.$$기본 지속 효과: 갈리오가 치명타를 입힐 때마다 지면을 강타해 대상 근처의 적에게 추가 마법 피해롤아이콘-주문력 신규를 입힙니다.$$피해량: 125 / 175 / 9001$기절 지속시간: 1.5 / 1.5 / 10$치명타 피해량: 70 / 100 / 1999|Socialite$Colossus$Bodyguard","Veigar|800 / 1440 / 2592|0 / 75|45 / 81 / 146|25 / 25|0.8|4|뿔보 폭풍|베이가가 근처 무작위 적에게 마법 피해를 입히는 뿔보를 소환합니다.$$뿔보 수: 20 / 30 / 99$피해량: 250 / 300 / 777|YordleLord","Viktor|850 / 1530 / 2754|0 / 140|50 / 90 / 162|35 / 35|0.8|4|혼돈의 광선|기본 지속 효과: 빅토르의 공격이 대상의 방어 태세를 무너뜨려, 6초 동안 방어력을 70% 감소시킵니다.$$빅토르가 다수의 특이점을 소환하여 전장을 가로지르는 죽음의 광선을 발사합니다. 죽음의 광선은 경로에 포착된 적들에게 마법 피해롤아이콘-주문력 신규를 입히고, 남은 보호막을 일정 %만큼 파괴합니다.$$피해량: 360 / 420 / 1500$광선의 수: 3 / 4 / 12$보호막 감소: 25 / 33 / 100|Chemtech$Arcanist","Silco|850 / 1530 / 2754|0 / 40|60 / 108 / 194.4|40 / 40|0.65|4|불안정한 혼합물|실코가 체력이 낮은 아군들에게 불안정한 약물을 부여합니다. 대상 아군들은 지속 시간 동안 최대 체력이 50%, 공격 속도가 증가하고 군중 제어 효과에 면역이 됩니다. 약물의 효과가 사라지면 불안정한 유닛이 폭발하며 사망해 주변 적에게 마법 피해를 입힙니다.$$추가 공격 속도: 50% / 125% / 666%$피해량: 250 / 500 / 5000$대상 수: 1 / 1 / 5$지속 시간:6 / 7 / 10|Mastermind$Scholar","Zeri|850 / 1530 / 2754|0 / 60|75 / 135 / 243|30 / 30|0.8|4|번개 방출|제리가 6초 동안 자신을 전기로 충전합니다. 충전된 동안 제리가 가장 먼 적을 조준하고, 제리의 기본 공격이 적을 관통하며, 사격할 때마다 돌진합니다.$$기본 지속 효과: 기본 공격 시 탄환을 여러발 발사해 각각 공격력의 18%에 해당하는 물리 피해와 추가 마법 피해롤아이콘-주문력 신규를 입힙니다.$$VIP 추가 효과: 전기 충전 효과가 전투가 끝날 때까지 지속됩니다.$$적중 시 피해량: 11 / 22 / 44$탄환 수: 5 / 5 / 30|Debonair$Sniper","Jayce|1000 / 1800 / 3240|0 / 60|85 / 153 / 275.4|30 / 30|0.75|1|하늘로! / 전격 폭발|하늘로!: 제이스가 스스로 과충전하여 3초 동안 보호막을 획득하며 저지할 수 없게 됩니다. 그리고 제이스가 두 번의 공격으로 전방의 적을 휩쓸어 공격력에 비례한 피해를 입히고 하늘로 도약해 대상을 내리찍습니다. 2칸 내에 있는 적들을 동일한 피해를 입고 5초 동안 방어력 및 마법저항력이 감소합니다.$$기본 지속 효과: 제이스가 40 방어력 및 마법 저항력을 얻습니다.$$방어력과 마법 저항력 감소량: 50% / 50% / 70%$보호막 흡수량: 375 / 550 / 3000$공격력: 160% / 170% / 1000%$$전격 폭발: 제이스가 가로로 같은 칸에 있는 아군에게 5초 동안 추가 공격 속도를 부여하는 가속 관문을 소환합니다. 그리고 다음 3회의 기본 공격으로 전기 구체를 발사하여 대상 주변 1칸 내에 공격력에 비례하는 물리 피해를 입힙니다. 세 번째 발사하는 전기 구체는 대상 주변 2칸 내에 피해를 입힙니다.$$기본 지속 효과: 제이스의 사거리가 4칸 증가하고 공격력이 증가합니다.$$공격력 획득: 45 / 60 / 500$공격 속도 %: 50% / 75% / 300%$공격력 %: 170% / 175% / 500%|Enforcer$Transformer$Innovator","Jinx|888 / 1598.4 / 2877.12|0 / 99|80 / 144 / 259.2|45 / 45|1|4|초강력 초토화 로켓|징크스가 자신의 로켓을 타고 하늘로 날아오른 후 가장 밀집되어 있는 적들을 향해 추락하며 중심에 있는 적들에게 마법 피해를 입히고 넓은 범위 내에 있는 모든 적들에게 50%만큼 피해를 입힙니다. 중심에서는 징크스를 제외한 모든 유닛을 5초 동안 불태워 대상 최대 체력에 비례한 고정 피해를 입히고 체력 회복 효과를 50% 감소시킵니다.$$그리고 전투가 끝날 때까지 자신의 로켓 발사기로 무기를 교체해 무작위 유닛을 공격합니다. 공격 시 폭발을 일으켜 대상 주변 작은 반경에 공격력에 비례하는 피해를 입힙니다.$$피해량: 450 / 700 / 8888$체력 비례 불태우기: 2% / 3% / 4%$공격력: 220% / 230% / 888%|Rivals$Scrap$Twinshot","Kaisa|850 / 1530 / 2754|75 / 150|60 / 108 / 194.4|30 / 30|1.2|3|이케시아 계절풍|카이사가 모든 적으로부터 물러나며 모든 적들에게 마법 피해를 입히는 여러 발의 미사일을 골고루 퍼붓습니다.$$이번 전투에서 카이사가 공격한 만큼 추가로 미사일을 발사합니다.$$기본 미사일: 12 / 18 / 100$미사일당 피해량: 70 / 90 / 180|Mutant$Challenger","TahmKench|1000 / 1800 / 3240|30 / 60|70 / 126 / 226.8|60 / 60|0.55|1|집어삼키기|탐 켄치가 대상을 배 속으로 집어삼켜 3초 동안 마법 피해를 입힙니다. 지속시간 동안 대상은 다른 피해에 무적 상태가 되며 탐 켄치가 받는 피해가 30% 감소합니다.$$배 속에서 대상이 사망하면 탐 켄치는 대상이 보유하고 있었던 무작위 조합 아이템이나 해당 대상의 비용을 골드로 뱉어냅니다. 대상이 사망하지 않을 경우 대상을 가장 멀리 있는 적에게 뱉고 충돌 시 짧게 기절시킵니다.$$대상이 군중 제어 효과에 면역이라면 기본 피해량의 50%의 마법 피해롤아이콘-주문력 신규를 입힙니다.$$피해량: 900 / 1350 / 30000|Glutton$Mercenary$Bruiser"];
const synergyList = ["ORIGINS|Rivals|경쟁자|이 특성은 정확히 하나의 경쟁자 유닛이 있을 때만 활성화됩니다. 경쟁자들은 서로 같은 팀에 있기 싫어하니까요.$$바이의 마나 소모량 20 감소$$징크스가 처치 관여 시 3초 동안 공격 속도가 40% 증가|_Vi$Jinx|바이$징크스","ORIGINS|Scrap|고물상|전투 시작 시 고물상 챔피언이 보유한 조합 아이템은 전투가 끝날 때까지 완성 아이템으로 변합니다. 또한 아군은 완성 아이템의 일부를 포함하여 보유한 모든 조합 아이템마다 보호막을 얻습니다.$$(2) 조합 아이템 1개를 완성 아이템으로 변경 + 조합 아이템 하나당 보호막 20$(4) 조합 아이템 3개를 완성 아이템으로 변경 + 조합 아이템 하나당 보호막 35$(6) 모든 조합 아이템을 완성 아이템으로 변경 + 조합 아이템 하나당 보호막 60|Ezreal$Ziggs$Blitzcrank$Ekko$Irelia$Jinx|이즈리얼$직스$블리츠크랭크$에코$이렐리아$징크스","ORIGINS|Glutton|대식가|(1) 준비 단계마다 한 번씩 탐 켄치가 대기석에 있는 아군 하나를 삼켜 주문력, 체력, 방어력 또는 마법 저항력 중 하나를 영구적으로 부여합니다.$$대기석에 있는 아군을 탐 켄치 위로 드래그해 입을 벌릴 때까지 기다렸다가 놓으면 탐 켄치가 아군을 먹습니다.|TahmKench|탐 켄치","ORIGINS|Mutant|돌연변이|(3) ???$(5) ???$(7) ???$돌연변이의 효과는 게임마다 변경됩니다.|Kassadin$RekSai$Malzahar$ChoGath$KhaZix$Kaisa|카사딘$렉사이$말자하$초가스$카직스$카이사","ORIGINS|Hextech|마법공학|전투 시작 시 및 전투 중 6초에 한 번씩 마공학 핵이 파동을 방출합니다. 파동에 맞은 아군 마법공학 챔피언은 4초 동안 지속되는 보호막을 얻습니다. 보호막이 남아있는 동안 기본 공격 적중 시 추가 마법 피해를 입힙니다. 보호막은 중첩되지 않습니다. 또한 마공학 핵에 있는 증강 하나당 보호막 피해 흡수량 및 추가 마법 피해가 20%씩 증가합니다.$$(2) 보호막 120, 마법 피해 15$(4) 보호막 170, 마법 피해 30$(6) 보호막 340, 마법 피해 60$(8) 보호막 700, 마법 피해 120|JarvanIV$Nocturne$Sejuani$Swain$Lucian$Sivir$Alistar|자르반 4세$녹턴$세주아니$스웨인$루시안$시비르$알리스타","ORIGINS|Syndicate|범죄 조직|아래의 아군이 그림자에 숨어 55의 방어력, 55의 마법 저항력, 20%의 모든 피해 흡혈 효과를 얻습니다.(입힌 모든 피해 비례 체력 회복)$$(3) 가장 낮은 체력을 가진 범죄 조직 챔피언$(5) 모든 범죄 조직 챔피언$(7) 아군 전체, 효과 60% 증가|Darius$Ashe$Zyra$Morgana$Braum$Ahri|다리우스$애쉬$자이라$모르가나$브라움$아리","ORIGINS|Socialite|사교계|사교계는 전장에 스포트라이트를 드리웁니다. 전투 시작 시 스포트라이트를 받는 유닛은 고유 추가 효과를 얻습니다.$$(1) 추가 피해 15%$(2) 위 효과 + 초당 마나 4$(3) 위 효과 + 입힌 모든 피해의 33%만큼 체력 회복$(5) 모든 추가 효과 두 배 증가|Gnar$Senna$Seraphine$Galio|나르$세나$세라핀$갈리오","ORIGINS|Clockwork|시계태엽|아군의 공격 속도가 상승합니다. 마공학 핵의 증강 수에 따라 효과가 더욱 강화됩니다.$$(2) 공격 속도 +10%+증강 하나당 +5%$(4) 공격 속도 +35%+증강 하나당 +10%$(6) 공격 속도 +80%+증강 하나당 +15%|Camille$Zilean$Orianna$Jhin|카밀$질리언$오리아나$진","ORIGINS|Debonair|연미복|연미복 챔피언은 추가 체력과 주문력을 얻습니다. 상점에 연미복 VIP가 등장할 확률이 증가합니다.$$연미복 VIP를 전장에 배치하면 고유 추가 효과가 활성화됩니다. VIP 판매 시 상점에 새로운 VIP가 등장할 수 있습니다.$$(3) 체력 200, 주문력 20$(5) 체력 450, 주문력 45$(7) 체력 800, 주문력 80|Brand$Syndra$Talon$Leona$Draven$Zeri|브랜드$신드라$탈론$레오나$드레이븐$제리","ORIGINS|Yordle|요들|(3) 플레이어와 전투를 마칠 때마다 무작위 요들 하나가 무료로 대기석에 추가$(6) 요들의 스킬 마나 소모량 25% 감소|Poppy$Ziggs$Corki$Lulu$Gnar$Vex|뽀삐$직스$코르키$룰루$나르$벡스","ORIGINS|YordleLord|요들 군주|(1) 요들 특성 효과|Veigar|베이가","ORIGINS|Mercenary|용병|플레이어를 상대로 전투에서 승리하면 열리는 보물 상자를 획득합니다. 플레이어를 상대하는 준비 단계가 시작되면 주사위를 굴려 상자에 전리품을 추가합니다. 상자를 더 오래 열지 않을수록 주사위에 행운이 증가합니다.$$(3) 주사위 2개 굴리기$(5) 주사위의 행운이 더욱 강력해집니다!$(7) 승리 시 추가 전리품을 부여하는 3번째 주사위 굴리기|Illaoi$Quinn$Gangplank$MissFortune$TahmKench|일라오이$퀸$갱플랭크$미스 포츈$탐 켄치","ORIGINS|Enforcer|집행자|(3) 집행자가 전투 시작 시 적을 기절시킵니다. 대상은 4초 후 또는 최대 체력의 40%를 잃은 다음 기절에서 벗어납니다. 군중 제어 효과에 면역인 적은 기절시키지 않습니다.|Caitlyn$Sejuani$_Vi$Jayce|케이틀린$세주아니$바이$제이스","ORIGINS|Chemtech|화학공학|화학공학 챔피언의 체력이 75% 아래로 떨어지면 화공 강화 상태가 되어 8초 동안 공격 속도가 상승하고, 받는 피해량이 20% 감소하며, 매초 최대 체력의 일부를 회복합니다.$$(3) 공격 속도 15%, 체력 4%$(5) 공격 속도 40%, 체력 7%$(7) 공격 속도 80%, 체력 10%$(9) 공격 속도 150%, 체력 18%|Singed$Twitch$Warwick$Tryndamere$Zac$Renata$Viktor|신지드$트위치$워윅$트린다미어$자크$레나타 글라스크$빅토르","ORIGINS|Mastermind|흑막|전투 시작 시 흑막은 바로 앞에 있는 아군 2명에게 30의 마나를 부여합니다. 이 효과는 중첩되지 않습니다|Silco|실코","CLASSES|Enchanter|강화술사|아군이 추가 마법 저항력을 얻습니다. 강화술사가 체력 회복 및 보호막 효과를 추가로 얻습니다.$$(2) 마법 저항력 20, 체력 회복량 및 보호막 흡수량 25%$(3) 마법 저항력 35, 체력 회복량 및 보호막 흡수량 40%$(4) 마법 저항력 50, 체력 회복량 및 보호막 흡수량 60%$(5) 마법 저항력 75, 체력 회복량 및 보호막 흡수량 100%|Lulu$Morgana$Senna$Orianna|룰루$모르가나$세나$오리아나","CLASSES|Colossus|거신|고유: 거신은 더 거대하고 강력하며, 추가 체력을 800 얻고 군중 제어 효과에 면역입니다. 거신 하나당 2개의 팀 슬롯이 필요합니다.$$(2) 거신 챔피언이 받는 피해 25% 감소$|ChoGath$Alistar$Galio|초가스$알리스타$갈리오","CLASSES|Bodyguard|경호대|경호대는 방어력이 증가합니다. 전투 시작 얼마 후, 보호막을 얻고 근처 적들을 도발해 경호대를 공격하게 합니다.$$(2) 방어력 80, 보호막 150$(4) 방어력 160, 보호막 350$(6) 방어력 250, 보호막 700$(8) 방어력 450, 보호막 1200|Darius$Poppy$Blitzcrank$Leona$Braum$Galio|다리우스$뽀삐$블리츠크랭크$레오나$브라움$갈리오","CLASSES|Bruiser|난동꾼|아군의 최대 체력이 증가합니다. 난동꾼은 두 배로 증가합니다.$$(2) 체력 125$(4) 체력 225$(6) 체력 450$(8) 체력 800|Illaoi$Sejuani$RekSai$Zac$ChoGath$_Vi$TahmKench|일라오이$세주아니$렉사이$자크$초가스$바이$탐 켄치","CLASSES|Challenger|도전자|도전자는 추가 공격 속도를 얻습니다. 처치 관여 시, 새로운 대상에게 돌진하며 2.5초 동안 공격 속도 상승 효과가 두 배가 됩니다.$$(2) 공격 속도 25%$(4) 공격 속도 55%$(6) 공격 속도 90%$(8) 공격 속도 150%|Camille$Warwick$Quinn$Tryndamere$Draven$Kaisa|카밀$워윅$퀸$트린다미어$드레이븐$카이사","CLASSES|Transformer|변형술사|제이스가 전방 2열에서는 근접 공격, 후방 2열에서는 원거리 공격을 합니다.|Jayce|제이스","CLASSES|Arcanist|비전 마법사|비전 마법사는 아군의 주문력을 증가시킵니다.$$(2) 아군의 주문력 20 증가$(4) 아군의 주문력 20 증가, 비전 마법사는 40 추가 증가$(6) 아군의 주문력 50 증가, 비전 마법사는 50 추가 증가$(8) 아군의 주문력 145 증가|Brand$Ziggs$Swain$Malzahar$Vex$Ahri$Viktor|브랜드$직스$스웨인$말자하$벡스$아리$빅토르","CLASSES|Twinshot|쌍발총|쌍발총 챔피언은 추가 공격력을 얻으며, 기본 공격하거나 스킬을 사용할 때 일정 확률로 두 번 공격합니다.$$(2) 공격력 10, 40% 확률$(3) 공격력 25, 55% 확률$(4) 공격력 45, 70% 확률$(5) 공격력 80, 100% 확률|Corki$Gangplank$Lucian$Jinx|코르키$갱플랭크$루시안$징크스","CLASSES|Assassin|암살자|암살자는 추가 치명타 피해량과 확률을 획득하며, 스킬에 치명타가 적용될 수 있습니다.$$(2) 치명타 확률 +10% 및 치명타 피해량 +20%$(4) 치명타 확률 +30% 및 치명타 피해량 +40%$(6) 치명타 확률 +50% 및 치명타 피해량 +60%|Nocturne$Twitch$Talon$Ekko$KhaZix|녹턴$트위치$탈론$에코$카직스","CLASSES|Sniper|저격수|저격수와 대상 사이에 놓인 칸 하나당 해당 저격수가 입히는 피해량이 증가합니다.$$(2) 추가 피해 8%$(4) 추가 피해 16%$(6) 추가 피해 30%|Caitlyn$Ashe$MissFortune$Jhin$Zeri|케이틀린$애쉬$미스 포츈$진$제리","CLASSES|Striker|타격대|타격대는 추가 공격력을 얻습니다.$$(2) 공격력 +30$(4) 공격력 +65$(6) 공격력 +110|JarvanIV$RekSai$Gnar$Sivir$Irelia|자르반 4세$렉사이$나르$시비르$이렐리아","CLASSES|Scholar|학자|2초마다 모든 아군의 마나가 회복됩니다.$$(2) 5마나$(4) 10마나$(6) 20마나|Kassadin$Syndra$Zyra$Renata$Silco|카사딘$신드라$자이라$레나타 글라스크$실코","CLASSES|Innovator|혁신가|혁신가는 기계 동료를 만들어 전장에서 함께 싸웁니다. 기계 동료는 아군 혁신가의 별 등급에 따라 추가 체력 및 공격력을 얻습니다.$$(3) 기계 풍뎅이$(5) 기계 곰$(7) 기계 용|Ezreal$Singed$Zilean$Ekko$Seraphine$Jayce|이즈리얼$신지드$질리언$에코$세라핀$제이스"];

function championListInit(){
    let innerHtml = "";
    for( let i=0; i<championNameList.length; i++ ){
        innerHtml += '<div class="championList championListName" onclick=selectChampion(';
        innerHtml += '"' + championNameList[i] + '",';
        innerHtml += '"' + championNameKrList[i] + '"';
        innerHtml += ')><img src="/img/tft/champion/';
        innerHtml += championNameList[i];
        innerHtml += '.png" class="championListImage">';
        innerHtml += championNameKrList[i];
        innerHtml += '</div>';
    }
    document.getElementById("championListField").innerHTML += innerHtml;
    document.getElementById("championSelect").style.display = "none";
}

function selectChampion( championName,  championNameKr ){
    let innerHtml = '';
    if( duplicateCheck(championName) && checkMaxSelect()){
        document.getElementById("championSelect").style.display = "";
        innerHtml += '<div class="championList championListName" id=select' + championName;
        innerHtml += ' onclick=deleteSelectChampion("select' + championName + '")>';
        innerHtml += '<img src="/img/tft/champion/' + championName + '.png" class="championListImage" alt="">' + championNameKr + '</div>';
        document.getElementById("selectedChampionField").innerHTML += innerHtml;
        setChampionSynergy();
    }
}

function setChampionSynergy(){
    let selectedChampionNodes = document.getElementById("selectedChampionField").childNodes;
    let i,j;
    document.getElementById("synergyDesc").innerHTML = "";
    for(i = 0; i < selectedChampionNodes.length; i++) {
        for (j = 0; j < champions.length; j++) {
            if (champions[j].indexOf(selectedChampionNodes[i].id.substring(6)) !== -1) {
                const abilityArray = champions[j].split("|");
                if (selectedChampionNodes[i].id.substring(6) === abilityArray[0]) {
                    viewSynergyListDesc(abilityArray[9]);
                }
            }
        }
    }
}

function checkMaxSelect(){
    let selectedChampionNumber = document.getElementById("selectedChampionField").childElementCount;
    if(selectedChampionNumber > 8){
        alert("선택할수 있는 챔피언수를 초과합니다.");
        return false;
    }
    return true;
}

function duplicateCheck( championName ){
    championName = "select"+championName;
    let championArea = document.getElementById("selectedChampionField");
    let selectedChampionNodes = championArea.childNodes;
    let i;

    for(i=0; i<selectedChampionNodes.length; i++){
        if(selectedChampionNodes[i].id === championName){
            alert("이미 추가한 챔피언 입니다.");
            return false;
        }
    }
    return true;
}

function deleteSelectChampion( championName ){
    let deleteNode = document.getElementById(championName);
    deleteNode.parentNode.removeChild(deleteNode);

    if(document.getElementById("selectedChampionField").childElementCount === 0){
        document.getElementById("championSelect").style.display = "none";
    }
    setChampionSynergy();
}

function viewSynergyListDesc( championSynergy ){
    let innerHtml = "";
    let synergy = championSynergy.split("$");
    let synergyStyle = ["synergyStyleNoStyle","synergyStyleBronze","synergyStyleSilver","synergyStyleGold","synergyStyleChromatic"];

    for(let i=0; i<synergy.length; i++){
        for(let j=0; j<synergyList.length; j++){
            if(synergyList[j].indexOf(synergy[i]) !== -1){
                const synergyArray = synergyList[j].split("|");
                if( synergy[i] === synergyArray[1]) {
                    const correctNumber = correctChampionNumber(synergyArray[1], synergyArray[4]);
                    const styleNumber = correctSynergyStyle(correctNumber, synergyArray[3], synergyArray[1]);
                    if( document.getElementById(synergy[i]) != null) {
                        const deleteNode = document.getElementById(synergy[i]);
                        deleteNode.parentNode.removeChild(deleteNode);
                    }
                    innerHtml += '<div class="contentsPadding contentsMarginw bg-light" id="' + synergy[i] + '">' +
                        '<div class="' + synergyStyle[styleNumber] + '"><img src="/img/tft/synergy/hollow/' + synergyArray[1] + '.png" alt="">' +
                        '<span class="synergyName">' + synergyArray[2] + '</span></div></div>';
                }
            }
        }
    }
    document.getElementById("synergyDesc").innerHTML += innerHtml;
}

function correctChampionNumber( synergyName, synergyChampionList ){

    let synergyChampion = synergyChampionList.split("$");
    let selectedChampion = document.getElementById("selectedChampionField").childNodes;
    let i, j;
    let correctChampionNumber = 0;

    for(i=0; i<synergyChampion.length; i++){
        for(j=0; j<selectedChampion.length; j++){
            if(synergyChampion[i] === selectedChampion[j].id.substring(6)){
                correctChampionNumber++;
            }
        }
    }

    return correctChampionNumber;
}

function correctSynergyStyle( correctNumber, synergyDesc, synergyName ){
    let controlSynergyStyle = ["(1)","(2)","(3)","(4)","(5)","(6)","(7)","(8)","(9)"];
    synergyDesc = synergyDesc.split("$");
    let synergyStyle = [];
    let i, j;
    for(i=0; i<controlSynergyStyle.length; i++){
        for(j=0; j<synergyDesc.length; j++){
            if(synergyDesc[j].indexOf(controlSynergyStyle[i]) !== -1){
                synergyStyle.push(synergyDesc[j].substring(1,2));
            }
        }
    }
    if( synergyStyle.length === 4 ){
        for(i = 3; i >= 0; i--){
            if(synergyStyle[i] <= correctNumber){
                return i + 1;
            }
        }
        return 0;
    }else if( synergyStyle.length === 3){
        for(i = 2; i >= 0; i--){
            if(i === 0){
                if(synergyStyle[i] <= correctNumber){
                    return i + 1;
                }
            }else{
                if(synergyStyle[i] <= correctNumber){
                    return i + 2;
                }
            }
        }
        return 0;
    }else if( synergyStyle.length === 2 ) {
        switch(synergyName){
            case "Yordle":
                if( 6 == correctNumber){
                    return 3;
                }else if(3 <= correctNumber){
                    return 1;
                }else{
                    return 0;
                }
                break;
            case "Colossus":
                if( correctNumber == 2 ){
                    return 3;
                }else{
                    return 0;
                }
                break;
        }

        return 0;
    }else if( synergyStyle.length === 1 ) {
        if( correctNumber == 3){
            return 3;
        }
        return 0;
    }else {
        return 3;
    }
}

function randomCombination(){
    let randomSelectNumber = document.getElementById("selectChampionNumber").value;
    let selectedChampionNumber = document.getElementById("selectedChampionField").childElementCount;
    let duplicateFlg = true;
    let massage = "랜덤 조합에 선택한 수와 이미 선택한 챔피언의 수가 10을 초과했습니다. 기존 선택한 챔피언을 모두 삭제하고 랜덤조합을 진행할려면 확인버튼을 누르세요\n" +
                  "진행을 취소 하려면 취소 버튼을 누르세요";

    let i = 0, j = 0;
    if(selectedChampionNumber + parseInt(randomSelectNumber) > 9) {
        if (confirm(massage)) {
            document.getElementById("selectedChampionField").innerHTML = "";
            document.getElementById("synergyDesc").innerHTML = "";
            while (i < randomSelectNumber) {
                let randomNumber = Math.floor(Math.random() * championNameList.length);
                let selectedChampion = document.getElementById("selectedChampionField").childNodes;
                selectedChampionNumber = document.getElementById("selectedChampionField").childElementCount;

                for(j=0; j<selectedChampionNumber; j++){
                    if(selectedChampion[j].id.substring(6) == championNameList[randomNumber]){
                        duplicateFlg = false;
                        break;
                    }
                    duplicateFlg = true;
                }
                if(duplicateFlg){
                    selectChampion(championNameList[randomNumber], championNameKrList[randomNumber]);
                    i++;
                }
            }
        }
    }else{
        while (i < randomSelectNumber) {
            let randomNumber = Math.floor(Math.random() * championNameList.length);
            let selectedChampion = document.getElementById("selectedChampionField").childNodes;
            selectedChampionNumber = document.getElementById("selectedChampionField").childElementCount;

            for(j=0; j<selectedChampionNumber; j++){
                if(selectedChampion[j].id.substring(6) == championNameList[randomNumber]){
                    duplicateFlg = false;
                    break;
                }
                duplicateFlg = true;
            }
            if(duplicateFlg){
                selectChampion(championNameList[randomNumber], championNameKrList[randomNumber]);
                i++;
            }
        }
    }
}

championListInit();
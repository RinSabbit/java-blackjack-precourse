> 시작 2:50
> 끝

## 블랙잭 미션

- 블랙잭 게임을 진행하는 프로그램을 구현한다.
- [ ] 블랙잭 게임은 딜러와 플레이어 중 **카드의 합이 21 또는 21에 가장 가 까운 숫자를 가지는 쪽이 이기는** 게임이다.

- [x] 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다.
    - [x] 배팅 금액은 양수이어야 한다.
- [x] 카드의 숫자 계산은 카드 숫자를 기본으로 하며,
    - [x] 예외로 **Ace는 1 또는 11**로 계산할 수 있다.
    - [x] **King, Queen, Jack은 각각 10**으로 계산한다.
- [x] 게임을 시작하면 *플레이어*는 두 장의 카드를 지급 받으며,
    - [x] 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가 깝게 만들면 이긴다.
    - [x] 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
    - [x] 단, 카드를 추가로 뽑아 **21을 초과할 경우 배팅 금액을 모두 잃게 된다.**
- [x] **처음 두 장의 카드 합이 21일 경우** 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다.
    - [x] 딜러와 플레이어가 모두 **동시 에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.**
- [x] *딜러*는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고,
    - [x] 17점 이상이면 추가로 받을 수 없다.
    - [x] 딜러가 21을 초과하면 그 시점까지 남아 햣있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받 는다.
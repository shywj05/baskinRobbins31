package package_Main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import package_Database.Database;
import package_VO.IcecreamVO;
import package_VO.NotifyVO;
import package_VO.OrderDetailsVO;
import package_VO.OrderInformationVO;
import package_VO.SizeVO;
import package_VO.UserVO;

public class View {
	private UserVO user = null;
	private final IServiceImpl iServiceImpl = new IServiceImpl();

	/**
	 * 문자열 입력 메서드
	 * 
	 * @author 이학재
	 * @return String - 입력받은 문자열
	 */
	String sInput() {
		Scanner scanner = new Scanner(System.in);
		return scanner.next().trim();
	}

	/**
	 * 숫자 입력 메서드
	 * 
	 * @author 이학재
	 * @return int - 입력받은 숫자
	 */
	int iInput() {
		int input;
		while (true) {
			try {
				Scanner scanner = new Scanner(System.in);
				input = scanner.nextInt();
				break;
			} catch (Exception e) {
				System.out.println();
				System.out.println("숫자만 입력해주세요.");
			}
		}
		return input;
	}

	/**
	 * 메인뷰
	 * 
	 * @author 정예진
	 */
	void startMethod() {
		String message = "";
		while (true) {
			System.out.println("┌─~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~─┐");
			System.out.println("∫ ┌─────────────────────────────────────┐ ∫");
			System.out.println("	배스킨라빈스31 에 오신 걸 환영합니다. ");
			System.out.println("∫ └─────────────────────────────────────┘ ∫");
			System.out.println("└─~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~─┘");
			System.out.println();
			System.out.println("[ 1 ] 로그인");
			System.out.println("[ 2 ] 회원가입");
			System.out.println("[ 0 ] 종료");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
				message = "";
			}

			switch (iInput()) {
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;
			case 1:
				loginView();
				break;
			case 2:
				insertUserView();
				break;
			default:
				message = "잘못 입력하셨습니다. 다시 입력해 주세요.";
			}
		}
	}

	/**
	 * 배너 표시
	 * 
	 * @author 박세웅
	 * @param string
	 *            - 표시해줄 문자열
	 */
	private void showBanner(String string) {
		System.out.println("");
		System.out.println("┌≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫┐");
		System.out.println("∮            Baskinrobbins31   ∮");
		System.out.println("└≪≪≪≪≪≪≪≪≪≪≪≪≪≪≪≪≪≪≪≪┘");
		System.out.println("\t" + string);
		System.out.println("∽∽∽∽∽∽∽∽∽∽∽∽∽∽∽∽∽∽∽∽∽∽∽∽∽∽∽∽∽");
		System.out.println();
	}

	/**
	 * 회원가입 -사용자 메서드
	 * 
	 * @author 이학재
	 */
	public void insertUserView() {
		// userVO를 부르기 위한 객체 불러서 추가를 해야하니까
		UserVO user = new UserVO();
		user.setPoint(-1);

		while (true) {
			System.out.println("┌∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞┐");
			if (user.getId() == null) {
				System.out.println("│ → 1. 아이디 설정			 │");
				System.out.println("│ 2. 이름 설정			 │");
				System.out.println("│ 3. 비밀번호 설정			 │");
				System.out.println("│ 4. 포인트 입력			 │");
			} else if (user.getName() == null) {
				System.out.println("│ 1. 아이디 설정			 │");
				System.out.println("│ → 2. 이름 설정			 │");
				System.out.println("│ 3. 비밀번호 설정			 │");
				System.out.println("│ 4. 포인트 입력			 │");
			} else if (user.getPw() == null) {
				System.out.println("│ 1. 아이디 설정			 │");
				System.out.println("│ 2. 이름 설정			 │");
				System.out.println("│ → 3. 비밀번호 설정			 │");
				System.out.println("│ 4. 포인트 입력			 │");
			} else {
				System.out.println("│ 1. 아이디 설정			 │");
				System.out.println("│ 2. 이름 설정			 │");
				System.out.println("│ 3. 비밀번호 설정			 │");
				System.out.println("│ → 4. 포인트 입력			 │");
			}
			System.out.println("└∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞┘");
			if (user.getId() == null) {
				user.setId(checkId());
				continue;
			} else if (user.getName() == null) {
				user.setName(scanName());
				continue;
			} else if (user.getPw() == null) {
				user.setPw(scanPw());
				continue;
			} else if (user.getPoint() == -1) {
				user.setPoint(scanPoint());
				continue;
			}
			break;
		}
		// 여기서 user를 매개변수로 구현시키는 곳의 인서트에 가서 넣어준게 true이면
		// 등록이 완료된거다.
		if (iServiceImpl.insertUser(user)) {
			System.out.println("회원 등록 완료");
		}
	}

	/**
	 * 아이디가 유일한지 확인 - 사용자 메서드
	 * 
	 * @author 이학재
	 * @return 아이디가 유일한지 확인하고 String 반환
	 */
	private String checkId() {
		String id;
		while (true) {
			id = scanId();
			boolean result = iServiceImpl.checkId(id);
			if (result) {
				break;
			} else {
				System.out.println("중복된 아이디입니다.");
			}
		}
		return id;
	}

	/**
	 * 아이디가 규칙에 맞는지 확인 - 사용자 메서드
	 * 
	 * @author 이학재
	 * @return 아이디의 규칙이 맞는지 확인하고 입력받은 값 반환
	 */
	private String scanId() {
		String input;
		String message = "";
		while (true) {
			System.out.println(">아이디 입력<");
			System.out.println(">8 ~ 20자리의 영문 또는 숫자 조합이 가능합니다<");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
			}
			input = sInput();
			if (RegEx.checkUser_id(input)) {
				break;
			}
			message = ">올바르지 않은 아이디 형식입니다<";
		}
		return input;
	}

	/**
	 * 이름 받아오는 메서드 - 사용자 메서드
	 * 
	 * @author 이학재
	 * @return 이름이 규칙에 맞는지 확인 후 String 반환
	 */
	private String scanName() {
		String input;
		String message = "";
		while (true) {
			System.out.println();
			System.out.println("→ 이름 입력 ←");
			System.out.println("2 ~ 17자의 한글만 입력해주세요. (※특수기호, 공백 사용 불가※ )");
			System.out.println("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
			}
			input = sInput();
			if (RegEx.checkUser_name(input)) {
				break;
			}
			message = "올바르지 않은 이름 형식입니다.";
		}
		return input;
	}

	/**
	 * 비밀번호 받아오는 메서드 - 사용자 메서드
	 * 
	 * @author 이학재
	 * @return 비밀번호 규칙확인을 위해 String 반환
	 */
	private String scanPw() {
		String message = "";
		String input;
		while (true) {
			System.out.println();
			System.out.println("→ 비밀번호 입력 ←");
			System.out.println("※ 8 ~ 20자의 숫자 또는 문자를 입력해주세요 ※");
			System.out.println("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
			}
			input = sInput();
			if (RegEx.checkUser_pw(input)) {
				break;
			}
			message = "X 올바르지 않은 비밀번호 형식입니다 X";
		}
		return input;
	}

	/**
	 * -포인트 받아오는 메서드 - 사용자 메서드
	 * 
	 * @author 이학재
	 * @return 충전하려는 금액 int로 반환
	 */
	private int scanPoint() {
		String message = "";
		int point;
		while (true) {
			System.out.println();
			System.out.println("포인트 입력");
			System.out.println();
			System.out.println("포인트를 입력해주세요.");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
			}
			point = iInput();

			if (point > 0) {
				break;
			}
			message = "올바른 숫자를 입력해주세요.";
		}
		return point;
	}

	/**
	 * 로그인 뷰 -관리자/사용자 메서드 -아이디 비밀번호값을 받아 database에서 비교
	 * 
	 * @author 박세웅
	 */
	private void loginView() {
		String userId = null;
		String userPw = null;
		String message = "";

		while (true) {
			System.out.println();
			if (userId == null) {
				System.out.println("→ 1. 아이디 입력");
				System.out.println("2. 비밀번호 입력");
			} else if (userPw == null) {
				System.out.println("1. 아이디 입력");
				System.out.println("→ 2. 비밀번호 입력");
			}
			System.out.println();

			if (userId == null) {
				System.out.println("아이디를 입력하세요.");
				if (!"".equals(message)) {
					System.out.println();
					System.out.println(message);
					message = "";
				}
				userId = sInput();
				continue;
			} else if (userPw == null) {
				System.out.println("비밀번호를 입력하세요.");
				userPw = sInput();
				continue;
			}

			Map<String, String> loginInfo = new HashMap<>();
			loginInfo.put("user_id", userId);
			loginInfo.put("user_pw", userPw);

			if (iServiceImpl.adminLogin(loginInfo)) {
				adminMainView();
				break;
			} else if (iServiceImpl.userLogin(loginInfo)) {
				user = iServiceImpl.selectUser(userId);
				userMainView();
				break;
			}

			message = "아이디 또는 비밀번호를 확인하세요.";
			userId = null;
			userPw = null;
		}
	}

	/**
	 * -관리자 메인 뷰 -관리자 메서드
	 * 
	 * @author 정예진
	 * 
	 */
	private void adminMainView() {
		String message = "";
		while (true) {
			showBanner("관리자 페이지");
			System.out.println("[ 1 ] 주문목록조회");
			System.out.println("[ 2 ] 아이스크림 재고 조회");
			System.out.println("[ 3 ] 아이스크림 사이즈 조회");
			System.out.println("[ 4 ] 회원 목록 조회");
			System.out.println("[ 5 ] 공지사항 조회");
			System.out.println("[ 0 ] 로그아웃");
			System.out.println();
			System.out.println("메뉴를 선택하세요.");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
				message = "";
			}

			switch (iInput()) {
			case 0:
				// 뒤로가기
				return;
			case 1:
				// 주문 목록 관리 메서드 호출
				orderInfoView();
				break;
			case 2:
				// 아이스크림 재고 관리 메서드 호출
				icecreamStockView();
				break;
			case 3:
				// 아이스크림 사이즈 관리 메서드 호출
				sizeManageView();
				break;
			case 4:
				// 회원 목록 관리 메서드 호출
				userListView();
				break;
			case 5:
				// 공지사항 관리 메서드 호출
				notifyView();
				break;
			default:
				message = "잘못 입력하셨습니다. 다시 입력해 주세요.";
			}
		}

	}

	/**
	 * -모든 주문 목록 조회 -관리자 메서드
	 * 
	 * @author 이학재
	 */
	private void orderInfoView() {
		List<OrderInformationVO> orderList = iServiceImpl
				.selectAllOrderInfomation();
		String message = "";
		while (true) {
			System.out
					.println("⊂==============================================================================⊃");
			if (orderList.size() > 0) {
				for (int i = 0; i < orderList.size(); i++) {
					OrderInformationVO orderInformation = orderList.get(i);
					UserVO user = iServiceImpl.selectUser(orderInformation
							.getUser_id());
					SizeVO size = iServiceImpl.selectSize(orderInformation
							.getSize_seq());
					System.out.print("[ " + (i + 1) + " ] " + "회원 ID : "
							+ orderInformation.getUser_id() + ", 이름 : "
							+ user.getName() + ", 사이즈명 : " + size.getName()
							+ ", 수령 방법 : " + orderInformation.getHowToPick());
					if (orderInformation.isRefund()) {
						System.out.print(" - 환불됨");
					}
					System.out.println();
				}
				System.out
						.println("⊂==============================================================================⊃");
			} else {
				System.out.println("주문 목록이 없습니다.");
			}
			System.out.println("┌~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~┐");
			System.out.println("│[ 0 ] 뒤로가기			   │");
			System.out.println("└~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~┘");

			System.out.println("  상세주문내역을 확인하고 싶은 회원의 번호를 입력하세요");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
				message = "";
			}

			int input = iInput();
			System.out.println("=====================================");
			if (input == 0) {
				return;
			} else if (input > 0 && input < orderList.size() + 1) {
				orderInfoDetails(orderList.get(input - 1).getSeq());
			} else {
				message = "잘못된 번호입니다.";
			}
		}
	}

	/**
	 * 주문 상세 내역 조회 - 관리자 메서드
	 * 
	 * @author 정예진
	 * @param order_seq
	 *            : 주문을 판별할 수 있는 번호
	 * 
	 */
	private void orderInfoDetails(int order_seq) {
		while (true) {
			OrderInformationVO order = iServiceImpl
					.selectOrderInfomation(order_seq);
			List<OrderDetailsVO> detail = iServiceImpl.selectOrderDetails(order
					.getSeq());
			System.out.println("아이디 : " + order.getUser_id());
			UserVO user = iServiceImpl.selectUser(order.getUser_id());
			SizeVO size = iServiceImpl.selectSize(order.getSize_seq());

			System.out.println("이름 : " + user.getName());
			System.out.println("사이즈명 : " + size.getName());
			System.out.println("수령 방법 : " + order.getHowToPick());
			System.out.println("환불 여부 : " + order.isRefund());

			System.out.print("맛 : ");
			for (OrderDetailsVO orderDetailsVO : detail) {
				int kinds = orderDetailsVO.getIcecream_seq();
				IcecreamVO icecream = iServiceImpl.selectIcecream(kinds);
				System.out.print('\t' + icecream.getKinds() + "\n");
			}
			System.out.println("수저 : " + order.getSpoonCount() + "개");
			System.out.println("∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪∪");
			System.out.println("[ 0 ] 뒤로가기");
			int input = iInput();

			if (input == 0) {
				return;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
			}
		}
	}

	/**
	 * -아이스크림 재고 관리 조회 -관리자 메서드
	 * 
	 * @author 정예진
	 */
	private void icecreamStockView() {
		String message = "";
		while (true) {
			List<IcecreamVO> icecream = iServiceImpl.selectAllIcecream();
			System.out.println("=============================");
			System.out.println("아이스크림 재고");
			System.out.println("=============================");
			System.out.println("[ 1 ] 추가하기");
			System.out.println("=============================");

			for (int i = 0; i < icecream.size(); i++) {
				System.out.println("[ " + (i + 2) + " ] "
						+ icecream.get(i).getKinds());
			}
			System.out.println("=============================");
			System.out.println("[ 0 ] 뒤로가기");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
			}
			System.out.println("=============================");
			int input = iInput();

			if (input == 0) {
				break;
			} else if (input == 1) {
				insertIcecreamMethod();
			} else if (input > 0 && input < icecream.size() + 2) {
				icecreamDetailsView(icecream.get(input - 2).getSeq());
			} else {
				message = "잘못된 입력입니다. 다시 입력해 주세요.";
			}
		}
	}

	/**
	 * -아이스크림 추가 -관리자 메서드
	 * 
	 * @author 정예진
	 */
	private void insertIcecreamMethod() {
		System.out.println("=============================");
		System.out.println("아이스크림 추가하기");
		System.out.println("=============================");
		System.out.println("추가할 아이스크림의 종류를 입력하세요.");
		String kinds = sInput();
		int stock = -1;

		System.out.println("추가할 아이스크림의 재고를 입력해 주세요.");
		while (true) {
			stock = iInput();
			if (stock < 0) {
				System.out.println("양수 값을 입력하세요.");
			} else {
				break;
			}
		}

		IcecreamVO icecream = new IcecreamVO();
		icecream.setKinds(kinds);
		icecream.setStock(stock);
		icecream.setSeq(++Database.icecream_seq);

		if (iServiceImpl.insertIcecream(icecream)) {
			System.out.println("추가되었습니다.");
		} else {
			System.out.println("추가에 실패하였습니다.");
		}

	}

	/**
	 * -아이스크림 상세정보 -관리자 메서드
	 * 
	 * @author 정예진
	 * @param icecream_seq
	 */
	private void icecreamDetailsView(int icecream_seq) {
		String message = "";

		while (true) {
			IcecreamVO icecream = iServiceImpl.selectIcecream(icecream_seq);
			System.out.println(icecream.getKinds());
			System.out.println("재고 : " + icecream.getStock() + "g");
			System.out.println("[ 1 ] 수정하기");
			System.out.println("[ 2 ] 삭제하기");
			System.out.println("[ 0 ] 뒤로가기");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
				message = "";
				System.out.println("=============================");
			}

			switch (iInput()) {
			case 0:
				return;
			case 1:
				updateIcecreamInfo(icecream_seq);
				System.out.println("-----------------------------------");
				break;
			case 2:
				if (deleteIcecreamInfo(icecream_seq) == 1) {
					return;
				}
				break;
			default:
				message = "잘못된 입력입니다. 다시 입력해 주세요.";
			}
		}
	}

	/**
	 * -아이스크림 정보 수정 -관리자 메서드
	 * 
	 * @author 박세웅
	 * @param icecream_seq
	 *            : icecream을 판별할 수 있는 고유번호
	 */
	private void updateIcecreamInfo(int icecream_seq) {
		String message = "";
		Map<String, Object> icecreamInfo = new HashMap<>();
		icecreamInfo.put("icecream_seq", icecream_seq);
		while (true) {
			IcecreamVO icecream = iServiceImpl.selectIcecream(icecream_seq);

			System.out.println("-----------------------------------");
			System.out.println("\t" + icecream.getKinds() + " 변경하기");
			System.out.println("\t~~~~~~~~~~~~~~~~");
			System.out.println("\t[ 1 ] 재고 : " + icecream.getStock());
			System.out.println("\t[ 0 ] 뒤로가기");
			System.out.println("-----------------------------------");
			if (!"".equals(message)) {
				System.out.println(message + "!!!");
			}

			switch (iInput()) {
			case 0:
				return;
			case 1:
				System.out.println("변경할 재고를 입력해주세요(단위:g)");
				icecreamInfo.put("icecream_stock", iInput());
				break;
			default:
				message = "번호 입력이 잘못됐어요";
				continue;
			}
			if (iServiceImpl.updateIcecream(icecreamInfo) > 0) {
				message = "변경 성공";
			} else {
				message = "변경 실패";
			}
		}

	}

	/**
	 * -아이스크림 정보 삭제 -관리자 메서드
	 * 
	 * @author 박세웅
	 * @param icecream_seq
	 *            : icecream을 판별할 수 있는 고유번호
	 * @return 삭제 성공 시 true, 실패 시 false
	 */
	private int deleteIcecreamInfo(int icecream_seq) {
		// sql → DBClass
		// boolean deleteIcecream(int icecream_seq)
		String message = "";
		while (true) {
			System.out.println("아이스크림 정보를 삭제하시겠습니까?");
			System.out.println("[ 1 ] Y");
			System.out.println("[ 2 ] N");
			System.out.println(message);
			String input = sInput();
			if ("1".equals(input) || "Y".equals(input) || "y".equals(input)) {
				int result = iServiceImpl.deleteIcecream(icecream_seq);
				if (result > 0) {
					System.out.println("삭제 완료");
				} else {
					System.out.println("삭제 실패");
				}
				return result;
			} else if ("2".equals(input) || "n".equals(input)
					|| "N".equals(input)) {
				System.out.println("삭제를 취소합니다");
				return 0;
			} else {
				System.out.println("올바르지 않은 입력입니다.");
			}
		}
	}

	/**
	 * 사이즈 관리 뷰 -관리자 메서드
	 * 
	 * @author 이학재
	 */
	private void sizeManageView() {
		String message = "";
		while (true) {
			List<SizeVO> sizeList = iServiceImpl.selectAllSize();
			System.out.println("====================");
			System.out.println("[ 1 ] 추가하기");
			System.out.println("====================");
			if (sizeList.size() > 0) {
				for (int i = 0; i < sizeList.size(); i++) {
					System.out
							.println("[ " + (i + 2) + " ] " + sizeList.get(i));
				}
			} else {
				System.out.println();
				System.out.println("등록된 사이즈가 없습니다.");
				System.out.println();
			}
			System.out.println("====================");
			System.out.println("[ 0 ] 뒤로가기");
			System.out.println("====================");
			System.out.println();
			System.out.println("메뉴를 선택하세요.");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
				message = "";
			}
			int input = iInput();
			if (input == 0) {
				return;
			} else if (input == 1) {
				insertSizeMethod();
			} else if (input > 0 && input < sizeList.size() + 2) {
				sizeDetailsView(sizeList.get(input - 2).getSeq());
			} else {
				message = "올바른 값을 입력하세요.";
			}
		}
	}

	/**
	 * 사이즈 추가 메서드 -관리자 메서드
	 * 
	 * @author 이학재
	 */
	private void insertSizeMethod() {
		SizeVO size = new SizeVO();
		String message = "";
		while (true) {
			if (size.getName() == null) {
				System.out.println("→ 1. 이름 입력");
				System.out.println("2. 용량 입력(g)");
				System.out.println("3. 선택 가능한 맛의 개수 입력");
				System.out.println("4. 가격 입력(원)");
			} else if (size.getGram() == 0) {
				System.out.println("1. 이름 입력");
				System.out.println("→ 2. 용량 입력(g)");
				System.out.println("3. 선택 가능한 맛의 개수 입력");
				System.out.println("4. 가격 입력(원)");
			} else if (size.getFlavorKinds() == 0) {
				System.out.println("1. 이름 입력");
				System.out.println("2. 용량 입력(g)");
				System.out.println("→ 3. 선택 가능한 맛의 개수 입력");
				System.out.println("4. 가격 입력(원)");
			} else if (size.getPrice() == 0) {
				System.out.println("1. 이름 입력");
				System.out.println("2. 용량 입력(g)");
				System.out.println("3. 선택 가능한 맛의 개수 입력");
				System.out.println("→ 4. 가격 입력(원)");
			}
			System.out.println(message);
			message = "";

			if (size.getName() == null) {
				System.out.println("사이즈명을 입력하세요.");
				size.setName(sInput());
			} else if (size.getGram() == 0) {
				System.out.println("용량(g)을 입력하세요.");
				int input = iInput();
				if (input < 1) {
					message = "올바르지 않은 용량(g)입니다.";
				} else {
					size.setGram(input);
				}
			} else if (size.getFlavorKinds() == 0) {
				System.out.println("맛의 수를 1 ~ 6 범위 내에서 선택해주세요.");
				int input = iInput();
				if (input < 1 || input > 6) {
					message = "유효하지 않은 숫자입니다.";
				} else {
					size.setFlavorKinds(input);
				}
			} else if (size.getPrice() == 0) {
				System.out.println("가격을 입력해주세요.");
				int input = iInput();
				if (input < 1) {
					message = "유효하지 않은 숫자입니다.";
				} else {
					size.setPrice(input);
				}
			} else {
				size.setSeq(++Database.size_seq);
				break;
			}
		}
		iServiceImpl.insertSize(size);
	}

	/**
	 * -사이즈 상세정보 메서드 -관리자 메서드
	 * 
	 * @author 이학재
	 * @param size_seq
	 *            : size를 판별할 수 있는 고유번호
	 */
	private void sizeDetailsView(int size_seq) {
		String message = "";
		while (true) {
			SizeVO size = iServiceImpl.selectSize(size_seq);
			System.out.println("========================");
			System.out.println("\t" + size.getName());
			System.out.println("========================");
			System.out.println("용량(g) : " + size.getGram());
			System.out.println("선택 가능한 맛의 개수 : " + size.getFlavorKinds());
			System.out.println("가격 : " + size.getPrice());
			System.out.println("========================");
			System.out.println();
			System.out.println("[ 1 ] 수정하기");
			System.out.println("[ 2 ] 삭제하기");
			System.out.println("[ 0 ] 이전으로");
			System.out.println("========================");
			System.out.println(message);
			message = "";

			switch (iInput()) {
			case 0:
				return;
			case 1:
				updateSizeInfo(size_seq);
				break;
			case 2:
				if (deleteSizeInfo(size_seq) == 1) {
					return;
				}
				break;
			default:
				message = "올바른 입력이 아닙니다.";
			}
		}
	}

	/**
	 * -사이즈 정보 업데이트 메서드 -관리자 메서드
	 * 
	 * @author 이학재
	 * @param size_seq
	 *            : size를 판별할 수 있는 고유번호
	 * @return 수정 성공 시 true, 실패 시 false
	 */
	private void updateSizeInfo(int size_seq) {
		String message = "";
		while (true) {
			SizeVO size = iServiceImpl.selectSize(size_seq);
			Map<String, Object> sizeInfo = new HashMap<>();
			sizeInfo.put("size_seq", size_seq);

			System.out.println("=============================");
			System.out.println("\t" + size.getName() + " 변경하기");
			System.out.println("=============================");
			System.out.println("[ 1 ] 사이즈명 : " + size.getName());
			System.out.println("[ 2 ] 용량(g) : " + size.getGram());
			System.out.println("[ 3 ] 선택 가능한 맛의 개수 : " + size.getFlavorKinds());
			System.out.println("[ 4 ] 가격 : " + size.getPrice());
			System.out.println("=============================");
			System.out.println("[ 0 ] 뒤로 가기");
			System.out.println("=============================");
			System.out.println();
			System.out.println("메뉴를 선택하세요");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
			}

			switch (iInput()) {
			case 0:
				return;
			case 1:
				System.out.println("변경하실 사이즈명을 입력하세요.");
				sizeInfo.put("size_name", sInput());
				break;
			case 2:
				System.out.println("변경하실 용량(g) 크기를 입력하세요.");
				sizeInfo.put("size_gram", iInput());
				break;
			case 3:
				System.out.println("변경하실 맛의 수를 입력하세요.");
				sizeInfo.put("size_flavorKinds", iInput());
				break;
			case 4:
				System.out.println("변경하실 가격을 입력하세요");
				sizeInfo.put("size_price", iInput());
				break;
			default:
				message = "올바른 번호를 입력하세요.";
				continue;
			}
			if (iServiceImpl.updateSize(sizeInfo) > 0) {
				message = "변경에 성공하였습니다.";
			} else {
				message = "변경에 실패하였습니다.";
			}
		}
	}

	/**
	 * -사이즈 정보 삭제 메서드 -관리자 메서드
	 * 
	 * @author 이학재
	 * @param size_seq
	 *            : size를 판별할 수 있는 고유번호
	 * @return 삭제 성공 시 true, 실패 시 false
	 */
	private int deleteSizeInfo(int size_seq) {
		String message = "";
		while (true) {
			System.out.println();
			System.out.println("정말 삭제하시겠습니까?");
			System.out.println("[ 1 ] Y");
			System.out.println("[ 2 ] N");
			System.out.println();
			System.out.println(message);
			System.out.println();
			String input = sInput();
			if ("1".equals(input) || "Y".equals(input) || "y".equals(input)) {
				int result = iServiceImpl.deleteSize(size_seq);
				if (result > 0) {
					System.out.println("삭제가 완료되었습니다.");
				} else {
					System.out.println("삭제에 실패하였습니다.");
				}
				return result;
			} else if ("2".equals(input) || "N".equals(input)
					|| "n".equals(input)) {
				System.out.println("삭제를 취소합니다.");
				return 0;
			} else {
				message = "올바르지 않은 입력입니다.";
			}
		}
	}

	/**
	 * -회원 목록 조회 -관리자 메서드
	 * 
	 * @author 정예진
	 */
	private void userListView() {
		List<UserVO> userList = iServiceImpl.selectAllUser();

		while (true) {
			System.out.println("====================================");
			if (userList.size() > 0) {
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).isActivate()) {
						System.out.println("[ " + (i + 1) + " ] "
								+ userList.get(i));
					}
				}
			} else {
				System.out.println("회원 목록이 비어있습니다.");
			}

			System.out.println("====================================");
			System.out.println("[ 0 ] 뒤로가기");
			System.out.println("====================================");
			int input = iInput();

			if (input == 0) {
				return;
			} else if (input > 0 && input < userList.size() + 1) {
				userDetailsView(userList.get(input - 1).getId());
			} else {
				System.out.println("잘못된 번호입니다.");
			}
		}
	}

	/**
	 * 선택된 회원 조회 -관리자 메서드
	 * 
	 * @author 정예진
	 * @param user_id
	 *            : user를 판별하기 위한 id
	 */
	private void userDetailsView(String user_id) {
		UserVO userInfo = iServiceImpl.selectUser(user_id);
		String message = "";
		while (true) {
			System.out.println("아이디 :\t" + userInfo.getId());
			System.out.println("이름    :\t" + userInfo.getName());
			System.out.println("포인트 :\t" + userInfo.getPoint());
			System.out.println();
			System.out.println("[ 0 ] 뒤로가기");
			if ("".equals(message)) {
				System.out.println();
				System.out.println(message);
			}
			if (iInput() == 0) {
				return;
			} else {
				message = "잘못된 번호입니다. 다시 입력해주세요.";
			}
		}
	}

	/**
	 * -공지 추가 -관리자 메서드
	 * 
	 * @author 박세웅
	 */
	private void insertNotify() {
		NotifyVO notify = new NotifyVO();
		String message = "";
		while (true) {
			if (notify.getTitle() == null) {
				System.out.println("→ 1. Title입력");
				System.out.println("2. 내용입력");
			} else if (notify.getContents() == null) {
				System.out.println("1. Title입력");
				System.out.println("→ 2. 내용입력");
			}

			if (notify.getTitle() == null) {
				System.out.println("Title을 입력하세요");
				notify.setTitle(sInput());
				continue;
			} else if (notify.getContents() == null) {
				System.out.println("내용을 입력하세요");
				notify.setContents(sInput());
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd", Locale.KOREA);
			Date date = new Date();

			notify.setSeq(++Database.notice_seq);
			notify.setDate(simpleDateFormat.format(date));

			iServiceImpl.insertNotify(notify);
			return;
		}
	}

	/**
	 * -공지 목록 조회 -관리자 메서드
	 * 
	 * @author 정예진
	 */
	private void notifyView() {
		while (true) {
			List<NotifyVO> notifyList = iServiceImpl.selectAllNotify();
			System.out.println();
			System.out.println("=============================");
			System.out.println("공지사항");
			System.out.println("=============================");
			System.out.println("[ 1 ] 추가하기");
			if (notifyList.size() > 0) {
				for (int i = 0; i < notifyList.size(); i++) {
					System.out.println("[ " + (i + 2) + " ] "
							+ notifyList.get(i));
				}
			} else {
				System.out.println("공지사항이 없습니다.");
			}
			System.out.println("=============================");
			System.out.println("[ 0 ] 뒤로가기\n");

			int notice_seq = iInput();

			if (notice_seq == 0) {
				return;
			} else if (notice_seq == 1) {
				insertNotify();
			} else if (notice_seq > 0 && notice_seq < notifyList.size() + 2) {
				notifyDetailsView(notifyList.get(notice_seq - 2).getSeq());
			} else {
				System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
			}
		}

	}

	/**
	 * -공지 상세 정보 -관리자 메서드
	 * 
	 * @author 박세웅
	 * @param notify_seq
	 *            : 공지를 판별하기 위한 고유번호
	 */
	private void notifyDetailsView(int notify_seq) {
		String message = "";
		while (true) {
			NotifyVO notify = iServiceImpl.selectNotify(notify_seq);
			System.out.println("────────────────────────────────");
			System.out.println("\t제목: " + notify.getTitle());
			System.out.println("\t내용: " + notify.getContents());
			System.out.println("\t날짜: " + notify.getDate());
			System.out.println("\t조회수: " + notify.getReadView());
			System.out.println("────────────────────────────────");
			System.out.println();
			System.out.println("[ 1 ] 수정하기");
			System.out.println("[ 2 ] 삭제하기");
			System.out.println("[ 0 ] 이전으로");
			System.out.println();
			System.out.println(message);

			switch (iInput()) {
			case 0:
				return;
			case 1:
				updateNotify(notify_seq);
				break;
			case 2:
				if (deleteNotify(notify_seq) == 1) {
					return;
				}
				break;
			default:
				message = "올바른 번호를 입력해주세요 ";
			}
		}
	}

	/**
	 * -공지 수정 -관리자 메서드
	 * 
	 * @author 박세웅
	 * @param notify_seq
	 *            : 공지를 판별하기 위한 고유번호
	 */
	private void updateNotify(int notify_seq) {
		String message = "";
		while (true) {
			NotifyVO notify = iServiceImpl.selectNotify(notify_seq);
			Map<String, Object> notifyObj = new HashMap<>();
			notifyObj.put("notify_seq", notify_seq);

			System.out.println("\t" + notify.getTitle() + " 변경하기");
			System.out.println("───────────────────────────────");
			System.out.println("[ 1 ] 제목 : " + notify.getTitle());
			System.out.println("[ 2 ] 내용 : " + notify.getContents());
			System.out.println("[ 0 ] 뒤로가기");
			System.out.println();
			System.out.println("메뉴를 선택해주세요");
			System.out.println();
			System.out.println(message);

			switch (iInput()) {
			case 0:
				return;
			case 1:
				System.out.println("변경할 타이틀 명을 써주세요");
				notifyObj.put("notify_title", sInput());
				break;
			case 2:
				System.out.println("변경할 내용을 써주세요");
				notifyObj.put("notify_contents", sInput());
				break;
			default:
				message = "올바른 번호를 입력해주세요";
				continue;
			}
			if (iServiceImpl.updateNotify(notifyObj) > 0) {
				message = "변경에 성공하였습니다.";
			} else {
				message = "변경실패";
			}
		}
	}

	/**
	 * -공지 삭제 -관리자 메서드
	 * 
	 * @author 박세웅
	 * @param notify_seq
	 *            : 공지를 판별하기 위한 고유번호
	 * @return 삭제 성공시 1, 실패 시 0
	 */
	private int deleteNotify(int notify_seq) {
		String message = "";
		while (true) {
			System.out.println("공지를 삭제하시겠습니까?");
			System.out.println("[ 1 ] Y");
			System.out.println("[ 2 ] N");
			System.out.println();
			System.out.println(message);
			System.out.println();
			String input = sInput();
			if ("1".equals(input) || "Y".equals(input) || "y".equals(input)) {
				int result = iServiceImpl.deleteNotify(notify_seq);
				if (result > 0) {
					System.out.println("삭제 완료");
					return 1;

				} else {
					System.out.println("삭제 실패");
				}
			} else if ("2".equals(input) || "N".equals(input)
					|| "n".equals(input)) {
				System.out.println("삭제를 취소합니다.");
				return 0;
			} else {
				message = "올바르지 않은 입력";
			}
		}
	}

	/**
	 * -회원 메인 뷰 -사용자 메서드
	 * 
	 * @author 박세웅
	 */
	public void userMainView() {
		String message = "";
		while (true) {
			if (user == null) {
				return;
			}
			System.out.println("--------------------");
			System.out.println("[ 1 ] 공지사항 조회");
			System.out.println("[ 2 ] 포인트 충전");
			System.out.println("[ 3 ] 주문하기");
			System.out.println("[ 4 ] 아이스크림 종류 조회");
			System.out.println("[ 5 ] 주문내역 조회");
			System.out.println("[ 6 ] 내 정보 수정");
			System.out.println("[ 0 ] 로그아웃");
			System.out.println("--------------------");

			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
				message = "￣￣￣￣￣￣￣￣￣￣￣￣￣￣";
			}

			switch (iInput()) {
			case 0:
				return;
			case 1:
				userNotifyView();
				break;
			case 2:
				pointCharge();
				break;
			case 3:
				sizeView();
				break;
			case 4:
				icecreamKindsView();
				break;
			case 5:
				orderHistoryView();
				break;
			case 6:
				myInfoView();
				break;
			default:
				message = "올바르지 않은 입력입니다.";
			}
		}
	}

	/**
	 * -공지사항 조회(회원) -사용자 메서드
	 * 
	 * @author 정예진
	 */
	private void userNotifyView() {
		List<NotifyVO> notifyList = iServiceImpl.selectAllNotify();
		while (true) {
			System.out.println();
			System.out.println("♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥");
			System.out.println("♡\t            공지사항	                    ♡");
			System.out.println("♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥♡♥");
			if (notifyList.size() > 0) {
				for (int i = 0; i < notifyList.size(); i++) {
					System.out.println("[ " + (i + 1) + " ] "
							+ notifyList.get(i));
				}
			} else {
				System.out.println("공지사항이 없습니다.");
			}
			System.out.println("===============================");
			System.out.println("[ 0 ] 뒤로가기");

			int input = iInput();

			if (input == 0) {
				return;
			} else if (input > 0 && input < notifyList.size() + 1) {
				userNotifyDetailsView(notifyList.get(input - 1).getSeq());
			} else {
				System.out.println(" ≫ 잘못된 입력입니다. 다시 입력해 주세요.");
			}
		}

	}

	/**
	 * -공지 상세 정보 -유저메서드
	 * 
	 * @author 정예진
	 * @param notify_seq
	 *            : 공지를 판별하기 위한 고유번호
	 */
	private void userNotifyDetailsView(int notify_seq) {
		NotifyVO notify = iServiceImpl.selectNotify(notify_seq);

		System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
		System.out.println("등록일 : " + notify.getDate());
		System.out.println("조회수 : " + notify.getReadView());
		System.out.println("내용 : " + notify.getContents());
		System.out.println("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
		System.out.println("[ 0 ]뒤로가기");
		while (true) {
			if (iInput() == 0) {
				return;
			} else {
				System.out.println("잘못된 입력입니다. 다시 입력해 주세요");
			}
		}
	}

	/**
	 * -포인트 충전 -유저메서드
	 * 
	 * @author 박세웅
	 */
	private void pointCharge() {
		System.out.println();
		System.out.println("충전할 포인트를 입력해 주세요");
		int input = iInput();
		Map<String, Object> userObj = new HashMap<>();
		userObj.put("user_id", user.getId());
		userObj.put("user_point", input);
		int result = iServiceImpl.addPoint(userObj);
		if (result <= 0) {
			System.out.println("포인트 충전이 되지 않았습니다.");
		}
		System.out.println("고객님의 잔여 포인트는: " + user.getPoint() + "입니다.");
	}

	/**
	 * -주문하기 -사용자 메서드
	 * 
	 * @author 이학재
	 */
	private void sizeView() {
		String message = "";
		while (true) {
			List<SizeVO> sizeList = iServiceImpl.selectAllSize();
			System.out.println();
			System.out.println("사이즈를 선택해주세요.");
			System.out
					.println("≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫≫");

			if (sizeList.size() > 0) {
				for (int i = 0; i < sizeList.size(); i++) {
					System.out.println("[ " + (i + 1) + " ]" + sizeList.get(i));
				}
			} else {
				System.out.println("등록된 사이즈가 없습니다. 관리자에게 문의하세요.");
			}
			System.out
					.println("┌=================================================┐");
			System.out.println("│[ 0 ]  뒤로가기					  │");
			System.out
					.println("└=================================================┘");
			System.out.println();
			if (!"".equals(message)) {
				System.out.println(message);
			}
			int input = iInput();
			if (input == 0) {
				return;
			} else if (input > 0 && input < sizeList.size() + 1) {
				if (user.getPoint() < sizeList.get(input - 1).getPrice()) {
					message = "구매하기 위한 포인트가 부족합니다.";
					continue;
				} else {
					user.setPoint(user.getPoint()
							- sizeList.get(input - 1).getPrice());
					orderDetails(sizeList.get(input - 1).getSeq());
				}
				return;
			} else {
				message = "올바른 입력이 아닙니다.";

			}
		}
	}

	/**
	 * -주문 상세 내역 입력 -사용자 메서드
	 * 
	 * @author 이학재
	 * @param size_seq
	 *            : 사이즈 설정을 위한 사이즈의 고유번호
	 */
	private void orderDetails(int size_seq) {
		SizeVO size = iServiceImpl.selectSize(size_seq);
		OrderInformationVO orderInformation = new OrderInformationVO();
		orderInformation.setSpoonCount(-1);
		orderInformation.setUser_id(user.getId());
		orderInformation.setSize_seq(size_seq);
		List<OrderDetailsVO> orderDetailsList = new ArrayList<>();
		int order_seq = ++Database.orderInformation_seq;
		while (true) {
			System.out.println(size.getName() + "사이즈");
			System.out.println();
			if (orderInformation.getSpoonCount() == -1) {
				System.out.println("→ 1. 숟가락 개수");
				System.out.println("2. 수령 방법");
				for (int i = 0; i < size.getFlavorKinds(); i++) {
					System.out.println(i + 1 + "번째 맛 선택");
				}
			} else if (orderInformation.getHowToPick() == null) {
				System.out.println("1. 숟가락 개수 : "
						+ orderInformation.getSpoonCount() + "개");
				System.out.println("→ 2. 수령 방법");
				for (int i = 0; i < size.getFlavorKinds(); i++) {
					System.out.println(i + 1 + "번째 맛 선택");
				}
			} else {
				System.out.println("1. 숟가락 개수 : "
						+ orderInformation.getSpoonCount());
				System.out.println("2. 수령 방법 : "
						+ orderInformation.getHowToPick());
				for (int i = 0; i < size.getFlavorKinds(); i++) {
					if (i < orderDetailsList.size()) {
						System.out.println(i
								+ 1
								+ "번째 맛 : "
								+ iServiceImpl.selectIcecream(
										orderDetailsList.get(i)
												.getIcecream_seq()).getKinds());
					} else if (i == orderDetailsList.size()) {
						System.out.println("→ " + (i + 1) + "번째 맛 선택");
					} else {
						System.out.println(i + 1 + "번째 맛 선택");
					}
				}
			}
			System.out.println();

			if (orderInformation.getSpoonCount() == -1) {
				orderInformation.setSpoonCount(spoonCount());
				continue;
			} else if (orderInformation.getHowToPick() == null) {
				orderInformation.setHowToPick(howToPick());
				continue;
			}

			if (size.getFlavorKinds() != orderDetailsList.size()) {
				OrderDetailsVO orderDetails = selectFlavor(size_seq);
				orderDetails.setOrder_seq(order_seq);
				orderDetails.setSeq(++Database.orderDetails_seq);
				orderDetailsList.add(orderDetails);
				continue;
			}

			orderInformation.setSeq(order_seq);

			if (!iServiceImpl.insertOrderInformation(orderInformation)) {
				return;
			}

			if (!iServiceImpl.insertOrderDetails(orderDetailsList)) {
				return;
			}

			break;
		}
	}

	/**
	 * -숫가락 갯수 골라라 -사용자 메서드
	 * 
	 * @author 이학재
	 * @return 선택한 스푼의 개수
	 */
	private int spoonCount() {
		String message = "";
		int input;
		while (true) {
			System.out.println("┌∝∝∝∝∝∝∝∝∝∝∝∝∝∝∝∝∝∝∝∝∝┐");
			System.out.println("│필요한 숫가락 갯수를 입력하세요.│");
			System.out.println("└∝∝∝∝∝∝∝∝∝∝∝∝∝∝∝∝∝∝∝∝∝┘");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
				message = "";
			}
			input = iInput();
			if (input < 0) {
				message = "올바르지 않은 값입니다.";
				continue;
			}
			break;
		}
		return input;
	}

	/**
	 * -맛도 골라라 -사용자 메서드
	 * 
	 * @author 이학재
	 * @return 선택한 아이스크림의 맛
	 */
	private OrderDetailsVO selectFlavor(int size_seq) {
		SizeVO size = iServiceImpl.selectSize(size_seq);
		List<IcecreamVO> icecream = iServiceImpl.selectAllIcecream();
		OrderDetailsVO orderDetails = new OrderDetailsVO();
		String message = "";
		while (true) {
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
			}
			System.out.println("아이스크림 종류");
			System.out.println("­---------------------------");
			for (int i = 0; i < icecream.size(); i++) {
				System.out.println("[" + (i + 1) + "]"
						+ icecream.get(i).getKinds());
			}
			System.out.println("­---------------------------");
			int input = iInput();
			if (0 < input && input < icecream.size() + 1) {
				if (size.getGram() / size.getFlavorKinds() > icecream.get(
						input - 1).getStock()) {
					message = "해당 아이스크림의 재고가 없어 선택할 수 없습니다.";
					continue;
				}
				Map<String, Object> order = new HashMap<>();
				order.put("icecream_seq", icecream.get(input - 1).getSeq());
				order.put("icecream_stock", icecream.get(input - 1).getStock()
						- size.getGram() / size.getFlavorKinds());
				iServiceImpl.updateIcecream(order);
				orderDetails.setIcecream_seq(icecream.get(input - 1).getSeq());
				break;
			} else {
				message = "올바르지 않은 값입니다. 다시 입력해주세요.\n";
			}
		}
		return orderDetails;
	}

	/**
	 * 수령 방법 선택 - 사용자 메서드
	 * 
	 * @author 이학재
	 * @return 선택한 수령 방법
	 */
	private String howToPick() {
		String message = "";
		while (true) {
			System.out.println("☞  수령방법선택 ");
			System.out.println("┌──────────┐");
			System.out.println("│[ 1 ] 매장   │");
			System.out.println("│[ 2 ] 포장   │");
			System.out.println("│[ 3 ] 배달   │");
			System.out.println("└──────────┘");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
			}
			switch (iInput()) {
			case 1:
				return "매장";
			case 2:
				return "포장";
			case 3:
				return "배달";
			default:
				message = "올바르지 않은 값입니다.";
			}
		}
	}

	/**
	 * -아이스크림 종류 조회 -사용자 메서드
	 * 
	 * @author 정예진
	 */
	private void icecreamKindsView() {
		while (true) {
			List<IcecreamVO> icecream = iServiceImpl.selectAllIcecream();
			System.out.println();
			System.out.println("----------------------");
			for (int i = 0; i < icecream.size(); i++) {
				System.out
						.println((i + 1) + ".  " + icecream.get(i).getKinds());
			}
			System.out.println("----------------------");
			System.out.println("[ 0 ] 뒤로가기");
			if (iInput() == 0) {
				break;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
			}
		}
	}

	/**
	 * -주문 내역 조회 -사용자 메서드
	 * 
	 * @author 정예진
	 */
	private void orderHistoryView() {
		while (true) {
			List<OrderInformationVO> orderList = iServiceImpl
					.selectAllOrderInformation(user.getId());
			if (orderList.size() == 0) {
				System.out.println("주문 내역이 없습니다.");
			}
			for (int i = 0; i < orderList.size(); i++) {
				SizeVO size = iServiceImpl.selectSize(orderList.get(i)
						.getSize_seq());
				System.out.println();
				System.out.println(" ☞ 번호를 눌러 정보 확인");
				System.out.println("===================");
				System.out.print("[ " + (i + 1) + " ] " + size.getName() + "  "
						+ size.getPrice() + "원 ");
				if (orderList.get(i).isRefund())
					System.out.print(" - 환불됨");
				System.out.println();
			}
			System.out.println("[ 0 ] 뒤로가기");
			System.out.println("===================");
			int input = iInput();
			if (input == 0) {
				return;
			} else if (input > 0 && input < orderList.size() + 1) {
				orderHistoryDetails(orderList.get(input - 1).getSeq());
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
			}
		}
	}

	/**
	 * -주문 상세 내역 조회 -사용자 메서드
	 * 
	 * @author 정예진
	 * @param order_seq
	 *            : 주문을 판별하기 위한 고유번호
	 * 
	 */
	private void orderHistoryDetails(int order_seq) {
		while (true) {
			OrderInformationVO order = iServiceImpl
					.selectOrderInfomation(order_seq);
			List<OrderDetailsVO> detail = iServiceImpl.selectOrderDetails(order
					.getSeq());
			System.out.println("--------------------------");
			System.out.println("아이디 : " + order.getUser_id());
			UserVO user = iServiceImpl.selectUser(order.getUser_id());
			SizeVO size = iServiceImpl.selectSize(order.getSize_seq());

			System.out.println("이름 : " + user.getName());
			System.out.println("사이즈명 : " + size.getName());
			System.out.println("수령 방법 : " + order.getHowToPick());
			System.out.println("환불 여부 : " + order.isRefund());

			System.out.print("맛 : ");
			for (OrderDetailsVO orderDetailsVO : detail) {
				int kinds = orderDetailsVO.getIcecream_seq();
				IcecreamVO icecream = iServiceImpl.selectIcecream(kinds);
				System.out.print('\t' + icecream.getKinds());
			}
			System.out.println("\n수저 : " + order.getSpoonCount() + "개");
			System.out.println("--------------------------");
			if (!order.isRefund()) {
				System.out.println("[ 1 ] 환불하기");
			}
			System.out.println("[ 0 ] 뒤로가기");
			int input = iInput();

			if (!order.isRefund()) {
				if (input == 1) {
					if (refund(order_seq) == 1) {
						return;
					} else {
						System.out.println("환불에 실패하였습니다.");
					}
				}
			}
			if (input == 0) {
				return;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
			}
		}

	}

	/**
	 * -환불하기 -사용자 메서드
	 * 
	 * @author 정예진
	 * @param orderInfo_seq
	 *            : 주문을 판별하기 위한 고유번호
	 * @return 환불 성공시 1, 실패 시 0
	 */
	private int refund(int orderInfo_seq) {
		// 환불
		int orderInfo = iServiceImpl.refundOrder(orderInfo_seq);

		// 유저에게 돈 돌려주기
		OrderInformationVO order = iServiceImpl
				.selectOrderInfomation(orderInfo_seq);
		SizeVO size = iServiceImpl.selectSize(order.getSize_seq());
		Map<String, Object> params = new HashMap<>();
		Integer changePoint = user.getPoint() + size.getPrice();
		if (orderInfo > 0) {
			params.put("user_id", user.getId());
			params.put("user_point", changePoint);
			iServiceImpl.updateUser(params);
			System.out.println("환불이 정상적으로 처리되었습니다.");

			return 1;

		} else {
			System.out.println("정상적으로 환불되지 않았습니다.");
		}
		return 0;
	}

	/**
	 * -내 정보 조회 -유저메서드
	 * 
	 * @author 박세웅
	 */
	private void myInfoView() {
		String message = "";
		while (true) {
			if (user == null) {
				return;
			}
			System.out.println();
			System.out.println("\t내  정  보  보  기");
			System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
			System.out.println("?¿☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆¿?");
			System.out.println("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
			System.out.println("\t아이디 : " + user.getId());
			System.out.println("\t이름 : " + user.getName());
			System.out.println("\t포인트 : " + user.getPoint());
			System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
			System.out.println("?¿☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆¿?");
			System.out.println("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
			System.out.println("[ 1 ] 이름 변경");
			System.out.println("[ 2 ] 비밀번호 변경");
			System.out.println("[ 3 ] 회원 탈퇴");
			System.out.println("[ 0 ] 뒤로 가기");
			System.out.println("=============================");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
				message = "";
			}

			switch (iInput()) {
			case 1:
				changeName();
				break;
			case 2:
				changePw();
				break;
			case 3:
				deleteUser();
				break;
			case 0:
				return;
			default:
				message = "잘못 입력하셨습니다.";
			}
		}
	}

	/**
	 * -Pw 변경 -사용자 메서드
	 * 
	 * @author 박세웅
	 */
	private void changePw() {
		String newPw = scanPw();
		Map<String, Object> params = new HashMap<>();

		params.put("user_id", user.getId());
		params.put("user_pw", newPw);

		int result = iServiceImpl.updateUser(params);
		if (result > 0) {
			System.out.println("변경에 성공하였습니다.");
			return;
		}
		System.out.println("변경에 실패하였습니다.");
	}

	/**
	 * -name 변경 -사용자 메서드
	 * 
	 * @author 박세웅
	 * @return 수정 성공 시 1, 실패 시 0 반환
	 */

	private void changeName() {
		String newName = scanName();
		Map<String, Object> params = new HashMap<>();

		params.put("user_id", user.getId());
		params.put("user_name", newName);

		int result = iServiceImpl.updateUser(params);
		if (result > 0) {
			System.out.println("변경에 성공하였습니다.");
			return;
		}
		System.out.println("변경에 실패하였습니다.");
	}

	/**
	 * 회원탈퇴 -사용자 메서드
	 * 
	 * @author 박세웅
	 */
	private void deleteUser() {
		String message = "";
		while (true) {
			System.out.println();
			System.out.println("___________________________");
			System.out.println("↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘");
			System.out.println("※ 정말 탈퇴하시겠습니까?");
			System.out.println("[ 1 ] Y");
			System.out.println("[ 2 ] N");
			System.out.println("↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗");
			System.out.println(message);
			String input = sInput();
			if ("1".equals(input) || "Y".equals(input) || "y".equals(input)) {
				int result = iServiceImpl.deleteUser(user.getId());
				if (result > 0) {
					System.out.println("탈퇴가 완료되었습니다.");
					System.out.println("이용해주셔서 감사합니다.");
					user = null;
				} else {
					System.out.println("회원 탈퇴에 실패하였습니다.");
				}
				return;
			} else if ("2".equals(input) || "N".equals(input)
					|| "n".equals(input)) {
				return;
			} else {
				message = "올바르지 않은 입력입니다.";
			}
		}
	}
}

package package_Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import package_VO.AdminVO;
import package_VO.IcecreamVO;
import package_VO.NotifyVO;
import package_VO.OrderDetailsVO;
import package_VO.OrderInformationVO;
import package_VO.SizeVO;
import package_VO.UserVO;

public class Database {
	//////////////////////////////////////
	//알멩이
	public static int size_seq = 8;
	public static int orderInformation_seq = 9;
	public static int notice_seq = 5;
	public static int orderDetails_seq = 23;
	public static int icecream_seq = 31;

	private final AdminVO admin = new AdminVO();
	// admin 계정 -yes

	private final List<NotifyVO> notifyList = new ArrayList<NotifyVO>();
	// 공지사항

	private final List<IcecreamVO> icecreamList = new ArrayList<IcecreamVO>();
	// 아이스크림 목록 -yes

	private final List<SizeVO> sizeList = new ArrayList<>();
	// 아이스크림 크기 목록 -yes

	private final List<UserVO> userList = new ArrayList<>();
	// 회원 목록 - yes

	private final List<OrderInformationVO> orderInformationList = new ArrayList<>();
	// 주문 정보

	private final List<OrderDetailsVO> orderDetailsList = new ArrayList<>();
	// 주문 상세 정보

	/**
	 * <code>adminLogin</code> 메서드는 해당 로그인 요청이 관리자 계정과 일치하는지 판별하는 메서드입니다.
	 * 
	 * @param loginInfo
	 *            : user_id, user_pw를 담은 Map
	 * @return 로그인에 성공한다면 true, 그렇지 않다면 false.
	 * @author 박세웅
	 */
	public boolean adminLogin(Map<String, String> loginInfo) {
		return admin.getId().equals(loginInfo.get("user_id")) && admin.getPw().equals(loginInfo.get("user_pw"));
	}

	/**
	 * <code>userLogin</code> 메서드는 해당 로그인 요청이 사용자 계정과 일치하는지 판별하는 메서드입니다.
	 * 
	 * @param loginInfo
	 *            : user_id, user_pw를 담은 Map
	 * @return 로그인에 성공한다면 true, 그렇지 않다면 false.
	 * @author 박세웅
	 */
	public boolean userLogin(Map<String, String> loginInfo) {
		if (selectUser(loginInfo.get("user_id")) == null) {
			return false;
		}
		return selectUser(loginInfo.get("user_id")).getPw().equals(loginInfo.get("user_pw")) && selectUser(loginInfo.get("user_id")).isActivate();
	}
	
	/**
	 * <code>selectAllNotify</code> 메서드는 모든 공지사항을 불러오기 위한 메서드입니다.
	 * 
	 * @return List : 모든 공지사항을 담은 List
	 * @author 정예진
	 */
	public List<NotifyVO> selectAllNotify() {
		return notifyList;
	}

	/**
	 * <code>readNotify</code> 메서드는 공지사항을 읽으며 조회수를 올리기 위한 메서드입니다.
	 * 
	 * @param seq
	 *            : 원하는 공지사항을 판별할 수 있는 숫자
	 * @return 한 개의 공지사항을 반환하며 동시에 조회수 증가
	 * @author 정예진
	 */
	public NotifyVO readNotify(int seq) {
		NotifyVO notify = selectNotify(seq);
		Map<String, Object> notifyObj = new HashMap<>();
		
		notifyObj.put("notify_seq", notify.getSeq());
		notifyObj.put("notify_readView", notify.getReadView() + 1);
		
		updateNotify(notifyObj);
		
		return notify;
	}
	
	/**
	 * <code>selectNotify</code> 메서드는 공지사항 하나를 선택하여 불러오기 위한 메서드입니다.
	 * 
	 * @param seq
	 *            : 원하는 공지사항을 판별할 수 있는 숫자
	 * @return 한 개의 공지사항을 반환
	 * @author 정예진
	 */
	public NotifyVO selectNotify(int seq) {
		for (NotifyVO notify : notifyList) {
			if (notify.getSeq() == seq) {
				return notify;
			}
		}
		return null;
	}
	
	/**
	 * <code>insertNotify</code> 메서드는 공지사항을 추가하기 위한 메서드입니다.
	 * 
	 * @param notify
	 *            : 새로운 공지사항의 정보를 담고있는 NotifyVO 객체
	 * @return 공지사항 등록 성공 시 true, 실패 시 false 반환
	 * @author 정예진
	 */
	public boolean insertNotify(NotifyVO notify) {
		return notifyList.add(notify);
	}
	
	/**
	 * <code>updateNotify</code> 메서드는 공지사항을 수정하기 위한 메서드입니다.
	 * 
	 * @param notifyObj : notify_seq, notify_title, notify_contents를 담은 Map
	 * @return 갱신에 성공한다면 1, 실패한다면 0 반환
	 * @author 정예진
	 */
	public int updateNotify(Map<String, Object> notifyObj) {

		if (notifyObj.get("notify_seq") == null) {
			return 0;
		}

		NotifyVO notify = selectNotify((Integer) notifyObj.get("notify_seq"));

		if (notifyObj.get("notify_title") != null) {
			notify.setTitle((String) notifyObj.get("notify_title"));
			return 1;
		} else if (notifyObj.get("notify_contents") != null) {
			notify.setContents((String) notifyObj.get("notify_contents"));
			return 1;
		} else if (notifyObj.get("notify_readView") != null) {
			notify.setReadView((Integer) notifyObj.get("notify_readView"));
		}
		return 0;
	}

	/**
	 * <code>deleteNotify</code> 메서드는 공지사항을 삭제하기 위한 메서드입니다.
	 * 
	 * @param seq
	 *            : 공지사항을 판별할 수 있는 숫자
	 * @return 삭제 성공 시 1, 실패 시 0 반환
	 * @author 정예진
	 */
	public int deleteNotify(int seq) {
		if (notifyList.remove(selectNotify(seq))) {
			return 1;
		}
		return 0;
	}

	/**
	 * <code>selectAllIcecream</code> 메서드는 모든 아이스크림의 정보를 불러오기 위한 메서드입니다.
	 * 
	 * @return List : 모든 아이스크림의 정보를 포함한 List
	 * @author 박세웅
	 */
	public List<IcecreamVO> selectAllIcecream() {
		List<IcecreamVO> icecreamList = new ArrayList<>();
		for (IcecreamVO icecream : this.icecreamList) {
			if (icecream.isActivate()) {
				icecreamList.add(icecream);
			}
		}
		return icecreamList;
	}

	/**
	 * <code>selectIcecream</code> 메서드는 선택한 아이스크림의 정보를 불러오기 위한 메서드입니다.
	 * 
	 * @param seq
	 *            : 원하는 아이스크림을 판별할 수 있는 숫자
	 * @return 아이스크림의 정보를 담고 있는 IcecreamVO 객체
	 * @author 박세웅
	 */
	public IcecreamVO selectIcecream(int seq) {
		for (IcecreamVO Icecream : icecreamList) {
			if (Icecream.getSeq() == seq) {
				return Icecream;
			}
		}
		return null;
	}

	/**
	 * <code>insertIcecream</code> 메서드는 새로운 아이스크림을 추가하기 위한 메서드입니다.
	 * 
	 * @param icecream
	 *            : 추가할 IcecreamVO 객체
	 * @return 추가에 성공하면 true, 실패하면 false 반환
	 * @author 박세웅
	 */
	public boolean insertIcecream(IcecreamVO icecream) {
		return icecreamList.add(icecream);
	}

	/**
	 * <code>updateIcecream</code> 메서드는 아이스크림의 정보를 업데이트 하기 위한 메서드입니다.
	 * 
	 * @param icecreamUpdateInfo
	 *            : icecream_seq, icecream_stock를 담은 Map
	 * @return 갱신에 성공한다면 1, 실패한다면 0 반환
	 * @author 박세웅
	 */
	public int updateIcecream(Map<String, Object> icecreamUpdateInfo) {
		if (icecreamUpdateInfo.get("icecream_seq") == null) {
			return 0;
		}

		IcecreamVO icecream = selectIcecream((Integer) icecreamUpdateInfo.get("icecream_seq"));

		if (icecreamUpdateInfo.get("icecream_stock") != null) {
			icecream.setStock((Integer) icecreamUpdateInfo.get("icecream_stock"));
			return 1;
		}
		return 0;
	}

	/**
	 * <code>deleteIcecream</code> 메서드는 아이스크림을 삭제하기 위한 메서드입니다.
	 * 
	 * @param seq
	 *            : 아이스크림을 판별할 수 있는 숫자
	 * @return 삭제 성공 시 1, 실패 시 0 반환
	 * @author 박세웅
	 */
	public int deleteIcecream(int seq) {
		IcecreamVO icecream = selectIcecream(seq);
		if (icecream == null) {
			return 0;
		}
		icecream.setActivate(false);
		return 1;
	}

	/**
	 * <code>selectAllSize</code> 메서드는 모든 아이스크림 크기의 정보를 불러오기 위한 메서드입니다.
	 * 
	 * @return List : 모든 아이스크림 크기의 정보를 포함한 List
	 * @author 박세웅
	 */
	public List<SizeVO> selectAllsize() {
		List<SizeVO> sizeList = new ArrayList<>();
		for (SizeVO size : this.sizeList) {
			if (size.isActivate()) {
				sizeList.add(size);
			}
		}
		return sizeList;
	}

	/**
	 * <code>selectSize</code>메서드는 선택한 아이스크림 크기의 정보를 가져오기 위한 메서드입니다.
	 * 
	 * @param seq
	 *            : 원하는 아이스크림 크기를 판별할 수 있는 숫자
	 * @return 아이스크림 크기의 정보를 담고 있는 SizeVO 객체
	 * @author 박세웅
	 */
	public SizeVO selectSize(int seq) {
		for (SizeVO sizeVO : sizeList) {
			if (sizeVO.getSeq() == seq)
				return sizeVO;
		}
		return null;
	}

	/**
	 * <code>insertSize</code> 메서드는 새로운 아이스크림 크기를 추가하기 위한 메서드입니다.
	 * 
	 * @param size
	 *            : 추가할 SizeVO 객체
	 * @return 추가에 성공하면 true, 실패하면 false 반환
	 * @author 박세웅
	 */
	public boolean insertSize(SizeVO size) {
		return sizeList.add(size);
	}

	/**
	 * <code>updateSize</code> 메서드는 아이스크림 사이즈의 정보를 업데이트 하기 위한 메서드입니다.
	 * 
	 * @param sizeInfo
	 *            : size_name, size_gram, size_flavorKinds, size_price를 담은 Map
	 * @return 갱신에 성공한다면 1, 실패한다면 0 반환
	 * @author 박세웅
	 */
	public int updateSize(Map<String, Object> sizeInfo) {
		SizeVO size = selectSize((Integer) sizeInfo.get("size_seq"));

		if (sizeInfo.get("size_name") != null) {
			size.setName((String) sizeInfo.get("size_name"));
			return 1;
		} else if (sizeInfo.get("size_gram") != null) {
			size.setGram((Integer) sizeInfo.get("size_gram"));
			return 1;
		} else if (sizeInfo.get("size_flavorKinds") != null) {
			size.setFlavorKinds((Integer) sizeInfo.get("size_flavorKinds"));
			return 1;
		} else if (sizeInfo.get("size_price") != null) {
			size.setPrice((Integer) sizeInfo.get("size_price"));
			return 1;
		}
		return 0;
	}

	/**
	 * <code>deleteSize</code> 메서드는 아이스크림 사이즈의 정보를 삭제하기 위한 메서드입니다.
	 * 
	 * @param seq
	 *            : 아이스크림 사이즈를 판별할 수 있는 숫자
	 * @return 삭제 성공 시 1, 실패 시 0 반환
	 * @author 박세웅
	 */
	public int deleteSize(int seq) {
		for (SizeVO sizeVO : sizeList) {
			if (sizeVO.getSeq() == seq) {
				sizeVO.setActivate(false);
				return 1;
			}
		}
		return 0;
	}

	/**
	 * <code>selectAllUser</code> 메서드는 모든 유저의 정보를 불러오기 위한 메서드입니다.
	 * 
	 * @return 모든 유저의 정보를 포함한 List
	 * @author 이학재
	 */
	public List<UserVO> selectAllUser() {
		List<UserVO> userList = new ArrayList<>();
		for (UserVO user : this.userList) {
			if (user.isActivate()) {
				userList.add(user);
			}
		}
		return userList;
	}

	/**
	 * <code>selectUser</code> 메서드는 선택한 유저의 정보를 불러오기 위한 메서드입니다.
	 * 
	 * @param id
	 *            : 사용자의 고유 id
	 * @return 사용자의 정보를 담고 있는 UserVO 객체
	 * @author 이학재
	 */
	public UserVO selectUser(String id) {
		for (UserVO user : userList) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	/**
	 * <code>userIdUniqueCheck</code> 메서드는 입력한 id가 유일한 값이 맞는지 판별하는 메서드입니다.
	 * 
	 * @param userId
	 *            : 유효성을 확인하기 위한 String 객체
	 * @return 유일한 값이라면 true, 그렇지 않은 경우라면 false 반환
	 */
	public boolean userIdUniqueCheck(String userId) {
		if (admin.getId().equals(userId)) {
			return false;
		}
		return selectUser(userId) == null;
	}

	/**
	 * <code>insertUser</code> 메서드는 새로운 사용자를 추가하기 위한 메서드입니다.
	 * 
	 * @param user
	 *            : 추가할 UserVO 객체
	 * @return 추가에 성공한다면 true, 실패한다면 false 반환
	 * @author 이학재
	 */
	//유저리스트 라는 리스트배열(?)안에 유저의 정보들을 저장해 놓는 거지 추가하는 거지 이미 있는 배열 마지막에 추가
	//됐으면 true 안 됐으면 false 반환
	public boolean insertUser(UserVO user) {
		return userList.add(user);
	}

	/**
	 * <code>updateUser</code> 메서드는 회원의 정보를 업데이트 하기 위한 메서드입니다.
	 * 
	 * @param userInfo
	 *            : user_pw, user_name, user_point를 담은 Map
	 * @return 갱신에 성공한다면 1, 실패한다면 0 반환
	 * @author 이학재
	 */
	public int updateUser(Map<String, Object> userInfo) {
		UserVO user = selectUser((String) userInfo.get("user_id"));
		if (user == null) {
			return 0;
		}
		if (userInfo.get("user_pw") != null) {
			user.setPw((String) userInfo.get("user_pw"));
			return 1;
		} else if (userInfo.get("user_name") != null) {
			user.setName((String) userInfo.get("user_name"));
			return 1;
		} else if (userInfo.get("user_point") != null) {
			user.setPoint((Integer) userInfo.get("user_point"));
			return 1;
		}
		return 0;
	}

	/**
	 * <code>addPoint</code> 메서드는 회원이 소지하고 있는 포인트를 추가하기 위한 메서드입니다.
	 * 
	 * @param userObj
	 *            : user_id, user_point를 담은 Map
	 * @return 갱신에 성공한다면 1, 실패한다면 0 반환
	 * @author 이학재
	 */
	public int addPoint(Map<String, Object> userObj) {
		UserVO user = selectUser((String) userObj.get("user_id"));
		userObj.put("user_point", (Integer) userObj.get("user_point") + user.getPoint());
		return updateUser(userObj);
	}

	/**
	 * <code>deleteUser</code> 메서드는 사용자를 삭제하기 위한 메서드입니다.
	 * 
	 * @param user_Id
	 *            : 사용자를 판별할 수 있는 id
	 * @return 삭제 성공 시 1, 실패 시 0 반환
	 */
	public int deleteUser(String user_Id) {
		UserVO user = selectUser(user_Id);
		if (user != null && user.isActivate()) {
			user.setActivate(false);
			return 1;
		}
		return 0;
	}
	
	/**
	 * <code>selectAllOrderInformation</code> 메서드는 모든 주문내역의 정보를 불러오기 위한 메서드입니다.
	 * 
	 * @return 모든 주문 정보를 담고 있는 List
	 * @author 정예진
	 */
	public List<OrderInformationVO> selectAllOrderInformation() {
		return orderInformationList;
	}

	/**
	 * <code>selectOrderInformation</code> 메서드는 선택한 주문내역의 정보를 불러오기 위한 메서드입니다.
	 * 
	 * @param seq
	 *            : 원하는 주문내역을 판별할 수 있는 숫자
	 * @return 주문내역의 정보를 담고 있는 OrderInformationVO 객체
	 * @author 정예진
	 */
	public OrderInformationVO selectOrderInformation(int seq) {
		for (OrderInformationVO orderInformation : orderInformationList) {
			if (orderInformation.getSeq() == seq) {
				return orderInformation;
			}
		}
		return null;
	}

	/**
	 * <code>selectOrderInformation</code> 메서드는 특정 유저에 대한 주문내역의 정보를 불러오기 위한
	 * 메서드입니다.
	 * 
	 * @param user_id
	 *            : 원하는 사용자를 판별할 수 있는 숫자
	 * @return 원하는 주문내역의 정보를 포함한 List
	 * @author 정예진
	 */
	public List<OrderInformationVO> selectOrderInformation(String user_id) {
		List<OrderInformationVO> orderInformationList = new ArrayList<>();
		for (OrderInformationVO orderInformation : this.orderInformationList) {
			if (user_id.equals(orderInformation.getUser_id())) {
				orderInformationList.add(orderInformation);
			}
		}
		return orderInformationList;
	}

	/**
	 * <code>insertOrderInformation</code> 메서드는 새로운 주문을 추가하기 위한 메서드입니다.
	 * 
	 * @param orderInformation
	 *            : 추가할 OrderInformationVO 객체
	 * @return 추가에 성공하면 true, 실패하면 false 반환
	 * @author 정예진
	 */
	public boolean insertOrderInformation(OrderInformationVO orderInformation) {
		return orderInformationList.add(orderInformation);
	}
	
	/**
	 * <code>refundOrder</code> 메서드는 주문을 환불하기 위한 메서드입니다.
	 * 
	 * @param orderInfo_seq : 환불할 주문의 고유번호
	 * @return 환불에 성공하면 1, 실패하면 0 반환
	 * @author 정예진
	 */
	public int refundOrder(int orderInfo_seq) {
		OrderInformationVO order = selectOrderInformation(orderInfo_seq);
		if (order == null) {
			return 0;
		}
		
		if (order.isRefund()) {
			return 0;
		}
		
		order.setRefund(true);
		return 1;
	}
	
	/**
	 * <code>selectOrderDetailsByOrderSeq</code> 메서드는 주문 정보를 기준으로 주문 상세 정보를 불러오기
	 * 위한 메서드입니다.
	 * 
	 * @param order_seq
	 *            : 원하는 주문 정보
	 * @return order_seq가 일치하는 주문 상세 정보를 포함한 List
	 * @author 정예진
	 */
	public List<OrderDetailsVO> selectOrderDetailsByOrderSeq(int order_seq) {
		List<OrderDetailsVO> OrderDetails = new ArrayList<>();
		for (OrderDetailsVO orderDetailsVO : orderDetailsList) {
			if (orderDetailsVO.getOrder_seq() == order_seq) {
				OrderDetails.add(orderDetailsVO);
			}
		}
		return OrderDetails;
	}
	
	/**
	 * <code>insertOrderDetails</code> 메서드는 주문 상세 정보를 추가하기 위한 메서드입니다.
	 * @param orderDetailsList : 추가할 OrderDetailsVO 객체
	 * @return 추가에 성공하면 true, 실패하면 false 반환
	 */
	public boolean insertOrderDetails(List<OrderDetailsVO> orderDetailsList) {
		return this.orderDetailsList.addAll(orderDetailsList);
	}
	
	// 아이스크림 목록 초기화 블럭
	{
		IcecreamVO i1 = new IcecreamVO();
		i1.setSeq(1);
		i1.setKinds("우낀소(우유속에 끼인 소보로)");
		i1.setStock(5000);
		icecreamList.add(i1);

		IcecreamVO i2 = new IcecreamVO();
		i2.setSeq(2);
		i2.setKinds("아이스 호떡");
		i2.setStock(5000);
		icecreamList.add(i2);

		IcecreamVO i3 = new IcecreamVO();
		i3.setSeq(3);
		i3.setKinds("아이스 붕어빵");
		i3.setStock(5000);
		icecreamList.add(i3);

		IcecreamVO i4 = new IcecreamVO();
		i4.setSeq(4);
		i4.setKinds("매시업스 시리얼");
		i4.setStock(5000);
		icecreamList.add(i4);

		IcecreamVO i5 = new IcecreamVO();
		i5.setSeq(5);
		i5.setKinds("오레오 쿠키 앤 크림");
		i5.setStock(5000);
		icecreamList.add(i5);

		IcecreamVO i6 = new IcecreamVO();
		i6.setSeq(6);
		i6.setKinds("메이플 월넛");
		i6.setStock(5000);
		icecreamList.add(i6);

		IcecreamVO i7 = new IcecreamVO();
		i7.setSeq(7);
		i7.setKinds("알폰소 망고");
		i7.setStock(5000);
		icecreamList.add(i7);

		IcecreamVO i8 = new IcecreamVO();
		i8.setSeq(8);
		i8.setKinds("다크 초코 나이트");
		i8.setStock(5000);
		icecreamList.add(i8);

		IcecreamVO i9 = new IcecreamVO();
		i9.setSeq(9);
		i9.setKinds("북극곰 폴리베어");
		i9.setStock(5000);
		icecreamList.add(i9);

		IcecreamVO i10 = new IcecreamVO();
		i10.setSeq(10);
		i10.setKinds("초콜릿 칩");
		i10.setStock(5000);
		icecreamList.add(i10);

		IcecreamVO i11 = new IcecreamVO();
		i11.setSeq(11);
		i11.setKinds("에스프레소 앤 크림");
		i11.setStock(5000);
		icecreamList.add(i11);

		IcecreamVO i12 = new IcecreamVO();
		i12.setSeq(12);
		i12.setKinds("럭키 카라멜 터틀");
		i12.setStock(5000);
		icecreamList.add(i12);

		IcecreamVO i13 = new IcecreamVO();
		i13.setSeq(13);
		i13.setKinds("엄마는 외계인");
		i13.setStock(5000);
		icecreamList.add(i13);

		IcecreamVO i14 = new IcecreamVO();
		i14.setSeq(14);
		i14.setKinds("아빠는 딸바봉");
		i14.setStock(5000);
		icecreamList.add(i14);

		IcecreamVO i15 = new IcecreamVO();
		i15.setSeq(15);
		i15.setKinds("아몬드 봉봉");
		i15.setStock(5000);
		icecreamList.add(i15);

		IcecreamVO i16 = new IcecreamVO();
		i16.setSeq(16);
		i16.setKinds("민트 초콜릿 칩");
		i16.setStock(5000);
		icecreamList.add(i16);

		IcecreamVO i17 = new IcecreamVO();
		i17.setSeq(17);
		i17.setKinds("슈팅스타");
		i17.setStock(5000);
		icecreamList.add(i17);

		IcecreamVO i18 = new IcecreamVO();
		i18.setSeq(18);
		i18.setKinds("사랑에 빠진 딸기");
		i18.setStock(5000);
		icecreamList.add(i18);

		IcecreamVO i19 = new IcecreamVO();
		i19.setSeq(19);
		i19.setKinds("초코나무 숲");
		i19.setStock(5000);
		icecreamList.add(i19);

		IcecreamVO i20 = new IcecreamVO();
		i20.setSeq(20);
		i20.setKinds("뉴욕 치즈케이크");
		i20.setStock(5000);
		icecreamList.add(i20);

		IcecreamVO i21 = new IcecreamVO();
		i21.setSeq(21);
		i21.setKinds("피스타치오 아몬드");
		i21.setStock(5000);
		icecreamList.add(i21);

		IcecreamVO i22 = new IcecreamVO();
		i22.setSeq(22);
		i22.setKinds("베리베리 스트로베리");
		i22.setStock(5000);
		icecreamList.add(i22);

		IcecreamVO i23 = new IcecreamVO();
		i23.setSeq(23);
		i23.setKinds("바람과 함께 사라지다");
		i23.setStock(5000);
		icecreamList.add(i23);

		IcecreamVO i24 = new IcecreamVO();
		i24.setSeq(24);
		i24.setKinds("레인보우 샤베트");
		i24.setStock(5000);
		icecreamList.add(i24);

		IcecreamVO i25 = new IcecreamVO();
		i25.setSeq(25);
		i25.setKinds("이상한 나라의 솜사탕");
		i25.setStock(5000);
		icecreamList.add(i25);

		IcecreamVO i26 = new IcecreamVO();
		i26.setSeq(26);
		i26.setKinds("초콜릿");
		i26.setStock(5000);
		icecreamList.add(i26);

		IcecreamVO i27 = new IcecreamVO();
		i27.setSeq(27);
		i27.setKinds("31요거트");
		i27.setStock(5000);
		icecreamList.add(i27);

		IcecreamVO i28 = new IcecreamVO();
		i28.setSeq(28);
		i28.setKinds("그린티");
		i28.setStock(5000);
		icecreamList.add(i28);

		IcecreamVO i29 = new IcecreamVO();
		i29.setSeq(29);
		i29.setKinds("체리쥬빌레");
		i29.setStock(5000);
		icecreamList.add(i29);

		IcecreamVO i30 = new IcecreamVO();
		i30.setSeq(30);
		i30.setKinds("바닐라");
		i30.setStock(5000);
		icecreamList.add(i30);

		IcecreamVO i31 = new IcecreamVO();
		i31.setSeq(31);
		i31.setKinds("초콜릿 무스");
		i31.setStock(5000);
		icecreamList.add(i31);
	}

	// 사이즈 초기화
	{
		SizeVO s1 = new SizeVO();
		s1.setFlavorKinds(1);
		s1.setName("싱글레귤러");
		s1.setGram(115);
		s1.setPrice(3200);
		s1.setSeq(1);
		sizeList.add(s1);

		SizeVO s2 = new SizeVO();
		s2.setFlavorKinds(1);
		s2.setName("싱글킹");
		s2.setGram(145);
		s2.setPrice(4000);
		s2.setSeq(2);
		sizeList.add(s2);

		SizeVO s3 = new SizeVO();
		s3.setFlavorKinds(2);
		s3.setName("더블주니어");
		s3.setGram(150);
		s3.setPrice(4300);
		s3.setSeq(3);
		sizeList.add(s3);

		SizeVO s4 = new SizeVO();
		s4.setFlavorKinds(2);
		s4.setName("더블레귤러");
		s4.setGram(230);
		s4.setPrice(6200);
		s4.setSeq(4);
		sizeList.add(s4);

		SizeVO s5 = new SizeVO();
		s5.setFlavorKinds(3);
		s5.setName("파인트");
		s5.setGram(320);
		s5.setPrice(8200);
		s5.setSeq(5);
		sizeList.add(s5);

		SizeVO s6 = new SizeVO();
		s6.setFlavorKinds(4);
		s6.setName("쿼터");
		s6.setGram(620);
		s6.setPrice(15500);
		s6.setSeq(6);
		sizeList.add(s6);

		SizeVO s7 = new SizeVO();
		s7.setFlavorKinds(5);
		s7.setName("패밀리");
		s7.setGram(960);
		s7.setPrice(22000);
		s7.setSeq(7);
		sizeList.add(s7);

		SizeVO s8 = new SizeVO();
		s8.setFlavorKinds(6);
		s8.setName("하프갤런");
		s8.setGram(1200);
		s8.setPrice(26500);
		s8.setSeq(8);
		sizeList.add(s8);
	}

	// 공지사항 초기화
	{
		NotifyVO n1 = new NotifyVO();
		n1.setSeq(1);
		n1.setContents("401번째 지점 오픈을 축하합니다 ~~~");
		n1.setDate("2020-04-01");
		n1.setReadView(10831);
		n1.setTitle("배스킨라빈스 401번째 지점 오픈");
		notifyList.add(n1);

		NotifyVO n2 = new NotifyVO();
		n2.setSeq(2);
		n2.setContents("배스킨라빈스는 매 31일마다 패밀리 → 하프갤런 사이즈업 이벤트를 진행합니다.");
		n2.setDate("2020-05-17");
		n2.setReadView(6678);
		n2.setTitle("31일의 이벤트");
		notifyList.add(n2);

		NotifyVO n3 = new NotifyVO();
		n3.setSeq(3);
		n3.setContents("빼빼로데이를 맞아 패밀리 이상 사이즈 구매 시 빼빼로 1:1 지급 이벤트를 진행합니다. (일부 매장 제외)");
		n3.setDate("2020-11-11");
		n3.setReadView(8376);
		n3.setTitle("빼빼로데이 기념 행사 안내");
		notifyList.add(n3);

		NotifyVO n4 = new NotifyVO();
		n4.setSeq(4);
		n4.setContents("소의 해를 맞아 인사드립니다. 새해 복 밚이 받으세요.");
		n4.setDate("2021-01-01");
		n4.setReadView(678);
		n4.setTitle("새해 복 많이 받으세요.");
		notifyList.add(n4);

		NotifyVO n5 = new NotifyVO();
		n5.setSeq(5);
		n5.setContents("우유 아이스크림과 소보로 아이스크림 속에 소보로 크럼블이 쏙쏙!");
		n5.setDate("2021-01-01");
		n5.setReadView(372);
		n5.setTitle("[이달의 맛] 우낀소 (우유속에 끼인 소보로)");
		notifyList.add(n5);
	}

	// UserVO 초기화 블럭
	{
		UserVO user1 = new UserVO();
		user1.setId("abcd01");
		user1.setName("오지란");
		user1.setPw("abcd0123");
		user1.setPoint(10000);
		user1.setActivate(true);
		userList.add(user1);

		UserVO user2 = new UserVO();
		user2.setId("qwer09");
		user2.setName("설마음");
		user2.setPw("qwer0912");
		user2.setPoint(7000);
		user2.setActivate(true);
		userList.add(user2);

		UserVO user3 = new UserVO();
		user3.setId("nex1032");
		user3.setName("김미소");
		user3.setPw("miso10321");
		user3.setPoint(53500);
		user3.setActivate(true);
		userList.add(user3);

		UserVO user4 = new UserVO();
		user4.setId("wow7777");
		user4.setName("이영옥");
		user4.setPw("duddhr777");
		user4.setPoint(43700);
		user4.setActivate(true);
		userList.add(user4);

		UserVO user5 = new UserVO();
		user5.setId("number1");
		user5.setName("이영준");
		user5.setPw("dudwns123");
		user5.setPoint(56700);
		user5.setActivate(true);
		userList.add(user5);

		UserVO user6 = new UserVO();
		user6.setId("yung22");
		user6.setName("이성연");
		user6.setPw("tjddus123");
		user6.setPoint(23500);
		user6.setActivate(true);
		userList.add(user6);

		UserVO user7 = new UserVO();
		user7.setId("sum09054");
		user7.setName("박유식");
		user7.setPw("mun09054");
		user7.setPoint(12100);
		user7.setActivate(true);
		userList.add(user7);

		UserVO user8 = new UserVO();
		user8.setId("han6666");
		user8.setName("고귀남");
		user8.setPw("jhan1421");
		user8.setPoint(15500);
		user8.setActivate(true);
		userList.add(user8);

		UserVO user9 = new UserVO();
		user9.setId("jia76");
		user9.setName("김지아");
		user9.setPw("13d5w312");
		user9.setPoint(34500);
		user9.setActivate(true);
		userList.add(user9);

		UserVO user10 = new UserVO();
		user10.setId("sheep133");
		user10.setName("양철");
		user10.setPw("sheep9999");
		user10.setPoint(31000);
		user10.setActivate(true);
		userList.add(user10);

		UserVO user11 = new UserVO();
		user11.setId("dkssud10");
		user11.setName("고민관");
		user11.setPw("13rlekffu");
		user11.setPoint(73800);
		user11.setActivate(true);
		userList.add(user11);

		UserVO user12 = new UserVO();
		user12.setId("rhksrPgud");
		user12.setName("김나무");
		user12.setPw("wlkdfj23");
		user12.setPoint(25300);
		user12.setActivate(true);
		userList.add(user12);

		UserVO user13 = new UserVO();
		user13.setId("epdlxj91");
		user13.setName("가자미");
		user13.setPw("vzli@3");
		user13.setPoint(11000);
		user13.setActivate(true);
		userList.add(user13);

		UserVO user14 = new UserVO();
		user14.setId("qpdltm23");
		user14.setName("황제성");
		user14.setPw("ghkdwp1@");
		user14.setPoint(6000);
		user14.setActivate(true);
		userList.add(user14);

		UserVO user15 = new UserVO();
		user15.setId("tjfrP19");
		user15.setName("쭈구리");
		user15.setPw("321EWQ");
		user15.setPoint(7500);
		user15.setActivate(true);
		userList.add(user15);

		UserVO user16 = new UserVO();
		user16.setId("rngus02");
		user16.setName("차두리");
		user16.setPw("sfj$z");
		user16.setPoint(3200);
		user16.setActivate(true);
		userList.add(user16);

		UserVO user17 = new UserVO();
		user17.setId("wkqk33");
		user17.setName("손흥민");
		user17.setPw("dlAfm2@#");
		user17.setPoint(85350);
		user17.setActivate(true);
		userList.add(user17);

		UserVO user18 = new UserVO();
		user18.setId("dPwls96");
		user18.setName("메시");
		user18.setPw("aslk24");
		user18.setPoint(15740);
		user18.setActivate(true);
		userList.add(user18);

		UserVO user19 = new UserVO();
		user19.setId("gkrwo98");
		user19.setName("이학재");
		user19.setPw("wejhd251!");
		user19.setPoint(82540);
		user19.setActivate(true);
		userList.add(user19);

		UserVO user20 = new UserVO();
		user20.setId("ypdnd95");
		user20.setName("장예진");
		user20.setPw("ewu456");
		user20.setPoint(57820);
		user20.setActivate(true);
		userList.add(user20);

		UserVO user21 = new UserVO();
		user21.setId("tlqkf18");
		user21.setName("retro");
		user21.setPw("dfghai%#");
		user21.setPoint(43580);
		user21.setActivate(true);
		userList.add(user21);
	}

	// 주문 정보 테이블
	{
		OrderInformationVO o1 = new OrderInformationVO();
		o1.setHowToPick("매장식사");
		o1.setSeq(1);
		o1.setSize_seq(4); // 더블레귤러
		o1.setSpoonCount(0);
		o1.setUser_id("sheep133");
		orderInformationList.add(o1);

		OrderInformationVO o2 = new OrderInformationVO();
		o2.setHowToPick("배달");
		o2.setSeq(2);
		o2.setSize_seq(4); // 더블레귤러
		o2.setSpoonCount(1);
		o2.setUser_id("jia76");
		orderInformationList.add(o2);

		OrderInformationVO o3 = new OrderInformationVO();
		o3.setHowToPick("방문포장");
		o3.setSeq(3);
		o3.setSize_seq(6); // 맛 4개
		o3.setSpoonCount(4);
		o3.setUser_id("sum09054");
		orderInformationList.add(o3);

		OrderInformationVO o4 = new OrderInformationVO();
		o4.setHowToPick("방문포장");
		o4.setSeq(4);
		o4.setSize_seq(5); // 맛 3개
		o4.setSpoonCount(1);
		o4.setUser_id("han6666");
		orderInformationList.add(o4);

		OrderInformationVO o5 = new OrderInformationVO();
		o5.setHowToPick("배달");
		o5.setSeq(5);
		o5.setSize_seq(3); // 맛 1개
		o5.setSpoonCount(0);
		o5.setUser_id("yung22");
		orderInformationList.add(o5);

		OrderInformationVO o6 = new OrderInformationVO();
		o6.setHowToPick("매장식사");
		o6.setSeq(6);
		o6.setSize_seq(7); // 맛 5개
		o6.setSpoonCount(7);
		o6.setUser_id("wow7777");
		orderInformationList.add(o6);

		OrderInformationVO o7 = new OrderInformationVO();
		o7.setHowToPick("매장식사");
		o7.setSeq(7);
		o7.setSize_seq(8); // 맛 6개
		o7.setSpoonCount(4);
		o7.setUser_id("number1");
		orderInformationList.add(o7);

		OrderInformationVO o8 = new OrderInformationVO();
		o8.setHowToPick("방문포장");
		o8.setSeq(8);
		o8.setSize_seq(6); // 맛 4개
		o8.setSpoonCount(4);
		o8.setUser_id("sum09054");
		orderInformationList.add(o8);

		OrderInformationVO o9 = new OrderInformationVO();
		o9.setHowToPick("배달");
		o9.setSeq(9);
		o9.setSize_seq(2); // 맛 1개
		o9.setSpoonCount(4);
		o9.setUser_id("sum09054");
		orderInformationList.add(o9);
	}

	// 주문 상세 정보 테이블
	{
		OrderDetailsVO od1 = new OrderDetailsVO();
		od1.setSeq(1);
		od1.setOrder_seq(1);
		od1.setIcecream_seq(1); // 무슨 맛
		orderDetailsList.add(od1);

		OrderDetailsVO od2 = new OrderDetailsVO();
		od2.setSeq(2);
		od2.setOrder_seq(1);
		od2.setIcecream_seq(7); // 무슨 맛
		orderDetailsList.add(od2);

		OrderDetailsVO od3 = new OrderDetailsVO();
		od3.setSeq(3);
		od3.setOrder_seq(2);
		od3.setIcecream_seq(2);
		orderDetailsList.add(od3);

		OrderDetailsVO od4 = new OrderDetailsVO();
		od4.setSeq(4);
		od4.setOrder_seq(2);
		od4.setIcecream_seq(11);
		orderDetailsList.add(od4);

		OrderDetailsVO od5 = new OrderDetailsVO();
		od5.setSeq(5);
		od5.setOrder_seq(3);
		od5.setIcecream_seq(3);
		orderDetailsList.add(od5);

		OrderDetailsVO od6 = new OrderDetailsVO();
		od6.setSeq(6);
		od6.setOrder_seq(3);
		od6.setIcecream_seq(31);
		orderDetailsList.add(od6);

		OrderDetailsVO od7 = new OrderDetailsVO();
		od7.setSeq(7);
		od7.setOrder_seq(3);
		od7.setIcecream_seq(4);
		orderDetailsList.add(od7);

		OrderDetailsVO od8 = new OrderDetailsVO();
		od8.setSeq(8);
		od8.setOrder_seq(3);
		od8.setIcecream_seq(27);
		orderDetailsList.add(od8);

		OrderDetailsVO od9 = new OrderDetailsVO();
		od9.setSeq(9);
		od9.setOrder_seq(4);
		od9.setIcecream_seq(9);
		orderDetailsList.add(od9);

		OrderDetailsVO od10 = new OrderDetailsVO();
		od10.setSeq(10);
		od10.setOrder_seq(4);
		od10.setIcecream_seq(17);
		orderDetailsList.add(od10);

		OrderDetailsVO od11 = new OrderDetailsVO();
		od11.setSeq(11);
		od11.setOrder_seq(4);
		od11.setIcecream_seq(8);
		orderDetailsList.add(od11);

		OrderDetailsVO od12 = new OrderDetailsVO();
		od12.setSeq(12);
		od12.setOrder_seq(5);
		od12.setIcecream_seq(24);
		orderDetailsList.add(od12);

		OrderDetailsVO od13 = new OrderDetailsVO();
		od13.setSeq(13);
		od13.setOrder_seq(6);
		od13.setIcecream_seq(31);
		orderDetailsList.add(od13);

		OrderDetailsVO od14 = new OrderDetailsVO();
		od14.setSeq(14);
		od14.setOrder_seq(6);
		od14.setIcecream_seq(27);
		orderDetailsList.add(od14);

		OrderDetailsVO od15 = new OrderDetailsVO();
		od15.setSeq(15);
		od15.setOrder_seq(6);
		od15.setIcecream_seq(1);
		orderDetailsList.add(od15);

		OrderDetailsVO od16 = new OrderDetailsVO();
		od16.setSeq(16);
		od16.setOrder_seq(6);
		od16.setIcecream_seq(3);
		orderDetailsList.add(od16);

		OrderDetailsVO od17 = new OrderDetailsVO();
		od17.setSeq(17);
		od17.setOrder_seq(6);
		od17.setIcecream_seq(5);
		orderDetailsList.add(od17);

		OrderDetailsVO od18 = new OrderDetailsVO();
		od18.setSeq(18);
		od18.setOrder_seq(7);
		od18.setIcecream_seq(2);
		orderDetailsList.add(od18);

		OrderDetailsVO od19 = new OrderDetailsVO();
		od19.setSeq(19);
		od19.setOrder_seq(7);
		od19.setIcecream_seq(5);
		orderDetailsList.add(od19);

		OrderDetailsVO od20 = new OrderDetailsVO();
		od20.setSeq(20);
		od20.setOrder_seq(7);
		od20.setIcecream_seq(6);
		orderDetailsList.add(od20);

		OrderDetailsVO od21 = new OrderDetailsVO();
		od21.setSeq(21);
		od21.setOrder_seq(7);
		od21.setIcecream_seq(15);
		orderDetailsList.add(od21);

		OrderDetailsVO od22 = new OrderDetailsVO();
		od22.setSeq(22);
		od22.setOrder_seq(7);
		od22.setIcecream_seq(14);
		orderDetailsList.add(od22);

		OrderDetailsVO od23 = new OrderDetailsVO();
		od23.setSeq(23);
		od23.setOrder_seq(7);
		od23.setIcecream_seq(18);
		orderDetailsList.add(od23);

		OrderDetailsVO od24 = new OrderDetailsVO();
		od23.setSeq(24);
		od23.setOrder_seq(8);
		od23.setIcecream_seq(20);
		orderDetailsList.add(od24);

		OrderDetailsVO od25 = new OrderDetailsVO();
		od23.setSeq(25);
		od23.setOrder_seq(8);
		od23.setIcecream_seq(11);
		orderDetailsList.add(od25);

		OrderDetailsVO od26 = new OrderDetailsVO();
		od23.setSeq(26);
		od23.setOrder_seq(8);
		od23.setIcecream_seq(19);
		orderDetailsList.add(od26);

		OrderDetailsVO od27 = new OrderDetailsVO();
		od23.setSeq(27);
		od23.setOrder_seq(8);
		od23.setIcecream_seq(7);
		orderDetailsList.add(od27);

		OrderDetailsVO od28 = new OrderDetailsVO();
		od23.setSeq(28);
		od23.setOrder_seq(9);
		od23.setIcecream_seq(25);
		orderDetailsList.add(od28);

	}

}

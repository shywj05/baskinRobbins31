package package_Main;

import java.util.List;
import java.util.Map;

import package_Database.Database;
import package_VO.IcecreamVO;
import package_VO.NotifyVO;
import package_VO.OrderDetailsVO;
import package_VO.OrderInformationVO;
import package_VO.SizeVO;
import package_VO.UserVO;

public class IServiceImpl implements IService {
	Database database = new Database();
//////////////////////////////////////////////////////////////
	//여기에서 인터페이스를 구현시키는 곳
	/**
	 * 회원가입 - 유저 정보 DB에 입력
	 * 
	 * @param user
	 * @return 성공 시 true, 실패 시 false 반환
	 * @author 이학재
	 */
	@Override
	public boolean insertUser(UserVO user) {
		return database.insertUser(user);
	}

	/**
	 * id 중복 여부와 조건을 충분히 만족하는지 확인
	 * 
	 * @param id
	 * @return 만족하면 true, 불만족하면 false 반환
	 */
	@Override
	public boolean checkId(String id) {
		return database.userIdUniqueCheck(id);
	}

	/**
	 * 관리자 계정 로그인
	 * 
	 * @param loginInfo
	 * @return 로그인 성공 시 true, 실패 시 false 반환
	 */
	public boolean adminLogin(Map<String, String> loginInfo) {
		return database.adminLogin(loginInfo);
	}

	/**
	 * 회원 계정 로그인
	 * 
	 * @param loginInfo
	 *            <"user_id", user_id>, <"user_pw", user_pw> 키/값을 전송하여 로그인 성공여부
	 *            반환받음
	 * @return 로그인 성공 시 true, 실패 시 false 반환
	 * @author 박세웅
	 */
	@Override
	public boolean userLogin(Map<String, String> loginInfo) {
		return database.userLogin(loginInfo);
	}

	/**
	 * 전체 주문내역 가져오기 - 관리자 메서드
	 * 
	 * @return List<OrderInformationVO>
	 * @author 이학재
	 */
	@Override
	public List<OrderInformationVO> selectAllOrderInfomation() {
		return database.selectAllOrderInformation();
	}

	/**
	 * 주문내역 하나 가져오기 - 관리자 / 유저 메서드
	 * 
	 * @param order_seq
	 * @return OrderInformationVO
	 * @author 정예진
	 */
	@Override
	public OrderInformationVO selectOrderInfomation(int order_seq) {
		
		return database.selectOrderInformation(order_seq);
	}

	/**
	 * 주문 상세내역 조회 - 관리자 / 유저 메서드
	 * 
	 * @param order_seq
	 * @return List<OrderDetailsVO>
	 * @author 정예진
	 */
	@Override
	public List<OrderDetailsVO> selectOrderDetails(int order_seq) {
		return database. selectOrderDetailsByOrderSeq(order_seq);
	}

	/**
	 * 사이즈 정보 전체 가져오기 - 관리자 / 유저 메서드
	 * 
	 * @return List<SizeVO>
	 * @author 이학재
	 */
	@Override
	public List<SizeVO> selectAllSize() {
		return database.selectAllsize();
	}

	/**
	 * 사이즈 정보 가져오기 - 관리자 / 유저 메서드
	 * 
	 * @param size_seq
	 * @return SizeVO
	 * @author 이학재
	 */
	@Override
	public SizeVO selectSize(int size_seq) {
		 return database.selectSize(size_seq);
	}

	/**
	 * 아이스크림 목록 전부 가져오기 - 관리자 / 유저 메서드
	 * 
	 * @return List<IcecreamVO>
	 * @author 이학재
	 */
	@Override
	public List<IcecreamVO> selectAllIcecream() {
		return database.selectAllIcecream();
	}

	/**
	 * 아이스크림 하나 가져오기 - 관리자 / 유저 메서드
	 * 
	 * @param icecream_seq
	 * @return IcecreamVO
	 * @author 박세웅
	 */
	@Override
	public IcecreamVO selectIcecream(int icecream_seq) {
		return database.selectIcecream(icecream_seq);
	}

	/**
	 * 새로운 아이스크림 정보 추가 - 관리자 메서드
	 * 
	 * @param icecream
	 * @return 성공하면 true, 실패하면 false 반환
	 * @author 박세웅
	 */
	@Override
	public boolean insertIcecream(IcecreamVO icecream) {
		return database.insertIcecream(icecream);
	}

	/**
	 * 아이스크림 정보 갱신 - 관리자 메서드
	 * 
	 * @param icecreamUpdateInfo
	 *            <"icecream_seq", icecream_seq>, <"stock", stock> 키와 값의 쌍
	 * @return 실패 시 0, 성공 시 양수 반환
	 * @author 이학재
	 */
	@Override
	public int updateIcecream(Map<String, Object> icecreamUpdateInfo) {
		return database.updateIcecream(icecreamUpdateInfo);
	}

	/**
	 * 아이스크림 정보 삭제 - 관리자 메서드
	 * 
	 * @param icecream_seq
	 * @return 실패 시 0, 성공 시 양수 반환
	 * @author 이학재
	 */
	@Override
	public int deleteIcecream(int icecream_seq) {
		return database.deleteIcecream(icecream_seq);
	}

	/**
	 * 사이즈 정보 추가 - 관리자 메서드
	 * 
	 * @param sizeVO
	 * @return 성공 시 true, 실패 시 false 반환
	 * @author 이학재
	 */
	@Override
	public boolean insertSize(SizeVO sizeVO) {
		return database.insertSize(sizeVO);
	}

	/**
	 * 사이즈 정보 업데이트 - 관리자 메서드
	 * 
	 * @param sizeInfo
	 *            <"size_seq", size_seq> <"size_name", size_name"> <"size_gram",
	 *            size_gram> <"size_flavorKinds", size_flavorKinds>
	 *            <"size_price", size_price>
	 * @return 실패 시 0, 성공 시 양수 반환
	 * @author 이학재
	 */
	@Override
	public int updateSize(Map<String, Object> sizeInfo) {
		return database.updateSize(sizeInfo);
	}

	/**
	 * 사이즈 정보 삭제 - 관리자 메서드
	 * 
	 * @param size_seq
	 * @return 실패 시 0, 성공 시 양수 반환
	 * @author 이학재
	 */
	@Override
	public int deleteSize(int size_seq) {
		return database.deleteSize(size_seq);
	}

	/**
	 * 모든 유저 정보 가져오기 - 관리자 메서드
	 * 
	 * @return List<UserVO>
	 * @author 정예진
	 */
	@Override
	public List<UserVO> selectAllUser() {
		
		return database.selectAllUser();
	}

	/**
	 * 선택한 유저 정보 가져오기 - 관리자 메서드
	 * 
	 * @param user_id
	 * @return UserVO
	 * @author 정예진
	 */
	@Override
	public UserVO selectUser(String user_id) {
		return database.selectUser(user_id);
	}

	/**
	 * 공지 입력 - 관리자 메서드
	 * 
	 * @param notify
	 * @return 성공 시 true, 실패 시 false 반환
	 * @author 박세웅
	 */
	@Override
	public boolean insertNotify(NotifyVO notify) {
		return database.insertNotify(notify);
	}

	/**
	 * 모든 공지 가져오기 - 관리자 / 유저 메서드
	 * 
	 * @return List<NotifyVO>
	 * @author 박세웅
	 */
	@Override
	public List<NotifyVO> selectAllNotify() {
		return database.selectAllNotify();
	}

	/**
	 * 선택한 공지 가져오기 - 관리자 / 유저 메서드
	 * 
	 * @param notify_seq
	 * @return NotifyVO
	 * @author 박세웅
	 */
	@Override
	public NotifyVO selectNotify(int notify_seq) {
		return database.readNotify(notify_seq);
	}

	/**
	 * 공지 정보 업데이트 - 관리자 메서드
	 * 
	 * @param notifyObj
	 * @return 실패 시 0, 성공 시 양수 반환
	 * @author 박세웅
	 */
	@Override
	public int updateNotify(Map<String, Object> notifyObj) {
		return database.updateNotify(notifyObj);
	}

	/**
	 * 공지 삭제 - 관리자 메서드
	 * 
	 * @param notify_seq
	 * @return 실패 시 0, 성공 시 양수 반환
	 * @author 박세웅
	 */
	@Override
	public int deleteNotify(int notify_seq) {
		return database.deleteNotify(notify_seq);
	}

	/**
	 * 포인트 충전 메서드 - 유저 메서드
	 * 
	 * @param userObj
	 * @return 실패 시 0, 성공 시 양수 반환
	 * @author 박세웅
	 */
	@Override
	public int addPoint(Map<String, Object> userObj) {
		return database.addPoint(userObj);
	}

	/**
	 * 주문 정보 추가 - 유저 메서드
	 * 
	 * @param orderInformation
	 * @return 성공 시 true, 실패 시 false 반환
	 * @author 이학재
	 */
	@Override
	public boolean insertOrderInformation(OrderInformationVO orderInformation) {
		return database.insertOrderInformation(orderInformation);
	}

	/**
	 * 주문 상세 정보 추가 - 유저 메서드
	 * 
	 * @param OrderDetailsList
	 * @return 성공 시 true, 실패 시 false 반환
	 * @author 이학재
	 */
	@Override
	public boolean insertOrderDetails(List<OrderDetailsVO> OrderDetailsList) {
		return database.insertOrderDetails(OrderDetailsList);
	}

	/**
	 * 나의 주문내역 전체 보기 - 유저 메서드
	 * 
	 * @param user_id
	 * @return List<OrderInformationVO>
	 * @author 정예진
	 */
	@Override
	public List<OrderInformationVO> selectAllOrderInformation(String user_id) {
		
		return database.selectOrderInformation(user_id);
	}


	/**
	 * 환불 - 유저 메서드
	 * 
	 * @param orderInfo_seq
	 * @return 실패 시 0, 성공 시 양수 반환
	 * @author 정예진
	 */
	@Override
	public int refundOrder(int orderInfo_seq) {
		return database.refundOrder(orderInfo_seq);
	}

	/**
	 * 유저 정보 업데이트 메서드
	 * 
	 * @param userInfo
	 *            <"user_id", user_id> <"user_pw", user_pw> <"user_name",
	 *            user_name>
	 * @return 실패 시 0, 성공 시 양수 반환
	 * @author 박세웅
	 */
	@Override
	public int updateUser(Map<String, Object> userInfo) {
		return database.updateUser(userInfo);
	}

	/**
	 * 유저 정보 삭제 메서드
	 * 
	 * @param user_id
	 * @return 실패 시 0, 성공 시 양수 반환
	 * @author 박세웅
	 */
	@Override
	public int deleteUser(String user_id) {
		return database.deleteUser(user_id);
	}

}
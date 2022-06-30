package controller;

import infra.Container;
import infra.Request;
import service.MemberService;

import java.util.Locale;

import static infra.Container.sc;

public class MemberController implements Controller{

    private MemberService memberService;

    public MemberController(){

        this.memberService = Container.memberService;

    }

    // /members/join
    @Override
    public void execute(Request request) {
        // /members/[target]
        switch (request.getTarget()){
            case "join":
                saveMember();
                break;
            case "login":
                login(request);
                break;
            case "logout":
                logout(request);
                break;
            default:
                System.out.println("올바른 요청을 보내주시기 바랍니다.");
                break;
        }
    }


    private void saveMember() {

        System.out.println("=== 회원가입 ===");

        System.out.print("아이디 : ");
        String loginId = sc.nextLine().trim();

        if(memberService.isExistsByLoginId(loginId)){
            System.out.println("이미 존재하는 아이디 입니다.");
            return;
        }

        System.out.print("비밀번호 : ");
        String password = sc.nextLine().trim();

        System.out.print("이름 : ");
        String name = sc.nextLine().trim();

        int memberId = memberService.saveMember(loginId, password, name);
        System.out.println(memberId + "번째 회원님 환영합니다.");

    }

    private void login(Request request) {

        System.out.println("=== 로그인 ===");

        System.out.print("아이디 : ");
        String loginId = sc.nextLine().trim();

        if(!memberService.isExistsByLoginId(loginId)){
            System.out.println("존재하지 않는 계정입니다.");
            return;
        }

        System.out.print("비밀번호 : ");
        String password = sc.nextLine().trim();

        if(!memberService.isCorrectInfo(loginId, password)){
            System.out.println("아이디 혹은 비밀번호가 정확하지 않습니다.");
            return;
        }

        request.login(loginId);

        System.out.println(loginId + "님 반갑습니다.");

    }

    private void logout(Request request) {

                // loginId
        String logonMember = request.getLogonMember();
        System.out.println("로그아웃 되었습니다. " + logonMember + "님 안녕히 가세요. ");

        request.logout();

    }


}

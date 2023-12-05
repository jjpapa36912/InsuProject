# bootakhae

심부름이 필요할 때 누구나 사용할 수 있는 솔루션 '부탁해' 입니다.

# Get Started (To Develop)
개발을 하기 위해서 필요한 단계는 아래와 같습니다.
1. Docker Engine Install (v24.0.7)
 - 다음 페이지에 접속하면 Docker Desktop을 설치할 수 있는데 도커 컨테이너 관리를 편리하게 해주는 SW다.
   https://docs.docker.com/desktop/install/windows-install/
   Docker Desktop을 설치하면 Docker Engine이 설치된다. 
   즉, Docker Desktop과 Docker Engine 버전은 다를 수 있다.
   참고) https://jjpapa36912.tistory.com/2
2. Spring Boot (v3.1.5)
3. gradle (v8.4)
4. JAVA (opnejdk v17.0.9)

# 코드 테스트
코드를 테스트 하기 위해 필요한 단계는 아래와 같습니다.
1. 터미널을 통해 bootakhae/Docker_Admin/mariadb 경로로 이동해서 'docker-compose up' 명령어를 실행한다.
   이 명령어를 통해 docker-compose.yml에 정의한 컨테이너들이 구동된다.
   docker-compose.yml에서 사용하는 global 변수들은 같은 경로의 .env파일에 정의되어 있다.
   여기에는 DB 접속 시 필요한 user, password등이 정의되어 있다. 본인의 로컬에 실행할 때 편의성을 위해 user, password를 바꾸어 'docker-compose up' 을 실행하는 것이 좋다.
3. 'localhost:83'으로 접속하면 phpMyAdmin화면이 뜨고 위 단계에서 설정한 user, password로 접속할 수 있다. 접속을 하면 DB 관리 화면을 볼 수 있다.
4. 현재 mariadb, phpMyAdmin 2개의 컨테이너가 구동되어 있는 상태이고, 다음으로 SpringBootApplication을 실행 시킨다.
5. 이 상태가 되면 테스트를 위한 준비가 끝난다.(phpMyAdmin은 선택사항이다. 단지 DB 관리를 GUI를 통해 쉽게 하기 위해 실행시킨 것이다)
6. 'localhost:8080/login'으로 접속하면 login 화면이 뜨게 되고 DB에 저장되어 있는 'login_id', 'password'정보를 통해 로그인 성공 여부를 테스트 할 수 있다.

package com.site.common.util;

public class Util {
	public static int nvl(String text) {
		return nvl(text, 0);
	}
	/**************************************************************************
	 * nvl() �޼���� ���ڿ��� ���ڷ� ��ȯ�ϴ� �޼���
	 * @param text ���ڷ� ��ȯ�� ���ڿ�
	 * @param def �ʱⰪ���� ����� ��(��ü��)
	 * ���� : ���� ó���� üũ���ܿ� ��üũ���ܷ� ����
	 * 		   üũ ���ܴ� ��������� / ��Ʈ��ũ ����� / �����ͺ��̽� �����
	 * 		   �������� ��üũ ���ܷ� �ν�
	 * @return int
	 **************************************************************************/
	public static int nvl(String text, int def) {
		int ret = def;
		try {
			ret = Integer.parseInt(text);
		} catch (Exception e) {
			// TODO: handle exception
			ret = def;
		}
		return ret;
	}
}


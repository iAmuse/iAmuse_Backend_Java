package com.iamuse.server.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.iamuse.server.responseVO.AppleReceiptVerifyResponse;

public class IOSPaymentVerifyRecepitUtil {
	
	public static void main(String[] args) throws Exception {
		
		String x = "1416301136000";

				DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

				long milliSeconds= Long.parseLong(x);
				System.out.println(milliSeconds);

				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(milliSeconds);
				String result=formatter.format(calendar.getTime());
				System.out.println("result \t"+result);
		
		
		
//		String str = "ewoJInNpZ25hdHVyZSIgPSAiQTUzQzloMEVpeFBWQnJ3TG5UV20zTXlpU1l1ckRzelVaSG5VemVtbElnZlNvWnM5RGRPc29GZTNpTUVoZmQ2dDd6Q2I1UjRQUVVmemtnREEzYXBTa3NoTGVoUkRkNWRValdxK1RzeW93SkNWcjkzRXFMdGJnT25GMEI5REIvVi9oTTNXbkNobzE2L2Y2VzF2S3pZM09qbWRwVzlNQmdCY1FXVTlIcGhBUHpqZGpmaDZEN2N0UWRIcTQrbitQeFpaOFhGUkZ3bFlQWC9QUzRXZE0xSDJwMnNnTFlZcWFBV3VVQ1dpZWREVkYvUFlxN1NBSFU2K1ZpOWNEYUp0dSthbVlSazRKWmlHY0ZJc3kyeUFzUHZZQklvR1dvUGJxZC9ZTzJUanAvUTdUVExaWlBlTnNUR1E4WjlTUVFDZFI1a1orNUpoTWZyT1hHV295cUJ4R2JFVkhrWUFBQVdBTUlJRmZEQ0NCR1NnQXdJQkFnSUlEdXRYaCtlZUNZMHdEUVlKS29aSWh2Y05BUUVGQlFBd2daWXhDekFKQmdOVkJBWVRBbFZUTVJNd0VRWURWUVFLREFwQmNIQnNaU0JKYm1NdU1Td3dLZ1lEVlFRTERDTkJjSEJzWlNCWGIzSnNaSGRwWkdVZ1JHVjJaV3h2Y0dWeUlGSmxiR0YwYVc5dWN6RkVNRUlHQTFVRUF3dzdRWEJ3YkdVZ1YyOXliR1IzYVdSbElFUmxkbVZzYjNCbGNpQlNaV3hoZEdsdmJuTWdRMlZ5ZEdsbWFXTmhkR2x2YmlCQmRYUm9iM0pwZEhrd0hoY05NVFV4TVRFek1ESXhOVEE1V2hjTk1qTXdNakEzTWpFME9EUTNXakNCaVRFM01EVUdBMVVFQXd3dVRXRmpJRUZ3Y0NCVGRHOXlaU0JoYm1RZ2FWUjFibVZ6SUZOMGIzSmxJRkpsWTJWcGNIUWdVMmxuYm1sdVp6RXNNQ29HQTFVRUN3d2pRWEJ3YkdVZ1YyOXliR1IzYVdSbElFUmxkbVZzYjNCbGNpQlNaV3hoZEdsdmJuTXhFekFSQmdOVkJBb01Da0Z3Y0d4bElFbHVZeTR4Q3pBSkJnTlZCQVlUQWxWVE1JSUJJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBUThBTUlJQkNnS0NBUUVBcGMrQi9TV2lnVnZXaCswajJqTWNqdUlqd0tYRUpzczl4cC9zU2cxVmh2K2tBdGVYeWpsVWJYMS9zbFFZbmNRc1VuR09aSHVDem9tNlNkWUk1YlNJY2M4L1cwWXV4c1FkdUFPcFdLSUVQaUY0MWR1MzBJNFNqWU5NV3lwb041UEM4cjBleE5LaERFcFlVcXNTNCszZEg1Z1ZrRFV0d3N3U3lvMUlnZmRZZUZScjZJd3hOaDlLQmd4SFZQTTNrTGl5a29sOVg2U0ZTdUhBbk9DNnBMdUNsMlAwSzVQQi9UNXZ5c0gxUEttUFVockFKUXAyRHQ3K21mNy93bXYxVzE2c2MxRkpDRmFKekVPUXpJNkJBdENnbDdaY3NhRnBhWWVRRUdnbUpqbTRIUkJ6c0FwZHhYUFEzM1k3MkMzWmlCN2o3QWZQNG83UTAvb21WWUh2NGdOSkl3SURBUUFCbzRJQjF6Q0NBZE13UHdZSUt3WUJCUVVIQVFFRU16QXhNQzhHQ0NzR0FRVUZCekFCaGlOb2RIUndPaTh2YjJOemNDNWhjSEJzWlM1amIyMHZiMk56Y0RBekxYZDNaSEl3TkRBZEJnTlZIUTRFRmdRVWthU2MvTVIydDUrZ2l2Uk45WTgyWGUwckJJVXdEQVlEVlIwVEFRSC9CQUl3QURBZkJnTlZIU01FR0RBV2dCU0lKeGNKcWJZWVlJdnM2N3IyUjFuRlVsU2p0ekNDQVI0R0ExVWRJQVNDQVJVd2dnRVJNSUlCRFFZS0tvWklodmRqWkFVR0FUQ0IvakNCd3dZSUt3WUJCUVVIQWdJd2diWU1nYk5TWld4cFlXNWpaU0J2YmlCMGFHbHpJR05sY25ScFptbGpZWFJsSUdKNUlHRnVlU0J3WVhKMGVTQmhjM04xYldWeklHRmpZMlZ3ZEdGdVkyVWdiMllnZEdobElIUm9aVzRnWVhCd2JHbGpZV0pzWlNCemRHRnVaR0Z5WkNCMFpYSnRjeUJoYm1RZ1kyOXVaR2wwYVc5dWN5QnZaaUIxYzJVc0lHTmxjblJwWm1sallYUmxJSEJ2YkdsamVTQmhibVFnWTJWeWRHbG1hV05oZEdsdmJpQndjbUZqZEdsalpTQnpkR0YwWlcxbGJuUnpMakEyQmdnckJnRUZCUWNDQVJZcWFIUjBjRG92TDNkM2R5NWhjSEJzWlM1amIyMHZZMlZ5ZEdsbWFXTmhkR1ZoZFhSb2IzSnBkSGt2TUE0R0ExVWREd0VCL3dRRUF3SUhnREFRQmdvcWhraUc5Mk5rQmdzQkJBSUZBREFOQmdrcWhraUc5dzBCQVFVRkFBT0NBUUVBRGFZYjB5NDk0MXNyQjI1Q2xtelQ2SXhETUlKZjRGelJqYjY5RDcwYS9DV1MyNHlGdzRCWjMrUGkxeTRGRkt3TjI3YTQvdncxTG56THJSZHJqbjhmNUhlNXNXZVZ0Qk5lcGhtR2R2aGFJSlhuWTR3UGMvem83Y1lmcnBuNFpVaGNvT0FvT3NBUU55MjVvQVE1SDNPNXlBWDk4dDUvR2lvcWJpc0IvS0FnWE5ucmZTZW1NL2oxbU9DK1JOdXhUR2Y4YmdwUHllSUdxTktYODZlT2ExR2lXb1IxWmRFV0JHTGp3Vi8xQ0tuUGFObVNBTW5CakxQNGpRQmt1bGhnd0h5dmozWEthYmxiS3RZZGFHNllRdlZNcHpjWm04dzdISG9aUS9PamJiOUlZQVlNTnBJcjdONFl0UkhhTFNQUWp2eWdhWndYRzU2QWV6bEhSVEJoTDhjVHFBPT0iOwoJInB1cmNoYXNlLWluZm8iID0gImV3b0pJbTl5YVdkcGJtRnNMWEIxY21Ob1lYTmxMV1JoZEdVdGNITjBJaUE5SUNJeU1ERTBMVEV4TFRFNElEQXdPalU0T2pVMklFRnRaWEpwWTJFdlRHOXpYMEZ1WjJWc1pYTWlPd29KSW5WdWFYRjFaUzFwWkdWdWRHbG1hV1Z5SWlBOUlDSTNaVEEyTUdObE5XRTJObUpqTkdKbU9UUTRORFF5T1RjeE5EWmpObUZpTVdJMllXTTRZVEl4SWpzS0NTSnZjbWxuYVc1aGJDMTBjbUZ1YzJGamRHbHZiaTFwWkNJZ1BTQWlNVEF3TURBd01ERXpNakF5T1RNek55STdDZ2tpWW5aeWN5SWdQU0FpTUM0eUlqc0tDU0owY21GdWMyRmpkR2x2YmkxcFpDSWdQU0FpTVRBd01EQXdNREkxTVRnd01qSTVNQ0k3Q2draWNYVmhiblJwZEhraUlEMGdJakVpT3dvSkltOXlhV2RwYm1Gc0xYQjFjbU5vWVhObExXUmhkR1V0YlhNaUlEMGdJakUwTVRZek1ERXhNell3TURBaU93b0pJblZ1YVhGMVpTMTJaVzVrYjNJdGFXUmxiblJwWm1sbGNpSWdQU0FpUlRrM01EZ3lOalF0TnpBelFTMDBPVVZHTFRsQk5URXRNVGM1TlVFeU1FUkROVFk1SWpzS0NTSndjbTlrZFdOMExXbGtJaUE5SUNKMmFXSmxkMmx5WlY5MWJteHZZMnRoY0hBaU93b0pJbWwwWlcwdGFXUWlJRDBnSWprME1qZzVOREExTVNJN0Nna2lZbWxrSWlBOUlDSmpiMjB1WTI5dGJXOXVVbUZ3YVdSemIyWjBMblpwWW1WM2FYSmxJanNLQ1NKd2RYSmphR0Z6WlMxa1lYUmxMVzF6SWlBOUlDSXhORGM1TkRVNU56WTJPRE16SWpzS0NTSndkWEpqYUdGelpTMWtZWFJsSWlBOUlDSXlNREUyTFRFeExURTRJREE1T2pBeU9qUTJJRVYwWXk5SFRWUWlPd29KSW5CMWNtTm9ZWE5sTFdSaGRHVXRjSE4wSWlBOUlDSXlNREUyTFRFeExURTRJREF4T2pBeU9qUTJJRUZ0WlhKcFkyRXZURzl6WDBGdVoyVnNaWE1pT3dvSkltOXlhV2RwYm1Gc0xYQjFjbU5vWVhObExXUmhkR1VpSUQwZ0lqSXdNVFF0TVRFdE1UZ2dNRGc2TlRnNk5UWWdSWFJqTDBkTlZDSTdDbjA9IjsKCSJlbnZpcm9ubWVudCIgPSAiU2FuZGJveCI7CgkicG9kIiA9ICIxMDAiOwoJInNpZ25pbmctc3RhdHVzIiA9ICIwIjsKfQ==";
		String str="ewoJInNpZ25hdHVyZSIgPSAiQXpNK0h0ZEpQZ2VaazJhOUM0a0VGWjlDVnJDYVVBM0pTSGw5czJ5NkhWVFhyY3dUZW1ET1lBaW1RWk9rQWJ3UFpFNHQybHNuR1ZHb3VDa0RHV3VkbHdRMlVoUU9YRnpjSVAzeUZLTWJaanRodzFvK0tEUHI1eXJiYzlzbDd5WThEWm9XMjF0dGJ1WEVCQzBCTC9OUnpROS9WR3pQbE9CWmhjMmJ3eWNBYzUxMkY2R3VVNjgwYTBubThWRjhSdTdEcnJVQjhHeTRNSTdmZVJ0WDlDRmwrVzMwWi9FWWV1UE5HRVdrRndSenc2RnJ0TVRYajFmT3Vzall1ME4wSXhabUZjLzZGdjJCeVZPbU54dmFzNWM2WUVzUnpQV0NsVWJSSEQ0bmM1UGRuYThaWGxOWVQ4cURWcG9RMHNGLy9vdWFsR0NtQWliT2NtNnM1bW1XN0pnZWl6Z0FBQVdBTUlJRmZEQ0NCR1NnQXdJQkFnSUlEdXRYaCtlZUNZMHdEUVlKS29aSWh2Y05BUUVGQlFBd2daWXhDekFKQmdOVkJBWVRBbFZUTVJNd0VRWURWUVFLREFwQmNIQnNaU0JKYm1NdU1Td3dLZ1lEVlFRTERDTkJjSEJzWlNCWGIzSnNaSGRwWkdVZ1JHVjJaV3h2Y0dWeUlGSmxiR0YwYVc5dWN6RkVNRUlHQTFVRUF3dzdRWEJ3YkdVZ1YyOXliR1IzYVdSbElFUmxkbVZzYjNCbGNpQlNaV3hoZEdsdmJuTWdRMlZ5ZEdsbWFXTmhkR2x2YmlCQmRYUm9iM0pwZEhrd0hoY05NVFV4TVRFek1ESXhOVEE1V2hjTk1qTXdNakEzTWpFME9EUTNXakNCaVRFM01EVUdBMVVFQXd3dVRXRmpJRUZ3Y0NCVGRHOXlaU0JoYm1RZ2FWUjFibVZ6SUZOMGIzSmxJRkpsWTJWcGNIUWdVMmxuYm1sdVp6RXNNQ29HQTFVRUN3d2pRWEJ3YkdVZ1YyOXliR1IzYVdSbElFUmxkbVZzYjNCbGNpQlNaV3hoZEdsdmJuTXhFekFSQmdOVkJBb01Da0Z3Y0d4bElFbHVZeTR4Q3pBSkJnTlZCQVlUQWxWVE1JSUJJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBUThBTUlJQkNnS0NBUUVBcGMrQi9TV2lnVnZXaCswajJqTWNqdUlqd0tYRUpzczl4cC9zU2cxVmh2K2tBdGVYeWpsVWJYMS9zbFFZbmNRc1VuR09aSHVDem9tNlNkWUk1YlNJY2M4L1cwWXV4c1FkdUFPcFdLSUVQaUY0MWR1MzBJNFNqWU5NV3lwb041UEM4cjBleE5LaERFcFlVcXNTNCszZEg1Z1ZrRFV0d3N3U3lvMUlnZmRZZUZScjZJd3hOaDlLQmd4SFZQTTNrTGl5a29sOVg2U0ZTdUhBbk9DNnBMdUNsMlAwSzVQQi9UNXZ5c0gxUEttUFVockFKUXAyRHQ3K21mNy93bXYxVzE2c2MxRkpDRmFKekVPUXpJNkJBdENnbDdaY3NhRnBhWWVRRUdnbUpqbTRIUkJ6c0FwZHhYUFEzM1k3MkMzWmlCN2o3QWZQNG83UTAvb21WWUh2NGdOSkl3SURBUUFCbzRJQjF6Q0NBZE13UHdZSUt3WUJCUVVIQVFFRU16QXhNQzhHQ0NzR0FRVUZCekFCaGlOb2RIUndPaTh2YjJOemNDNWhjSEJzWlM1amIyMHZiMk56Y0RBekxYZDNaSEl3TkRBZEJnTlZIUTRFRmdRVWthU2MvTVIydDUrZ2l2Uk45WTgyWGUwckJJVXdEQVlEVlIwVEFRSC9CQUl3QURBZkJnTlZIU01FR0RBV2dCU0lKeGNKcWJZWVlJdnM2N3IyUjFuRlVsU2p0ekNDQVI0R0ExVWRJQVNDQVJVd2dnRVJNSUlCRFFZS0tvWklodmRqWkFVR0FUQ0IvakNCd3dZSUt3WUJCUVVIQWdJd2diWU1nYk5TWld4cFlXNWpaU0J2YmlCMGFHbHpJR05sY25ScFptbGpZWFJsSUdKNUlHRnVlU0J3WVhKMGVTQmhjM04xYldWeklHRmpZMlZ3ZEdGdVkyVWdiMllnZEdobElIUm9aVzRnWVhCd2JHbGpZV0pzWlNCemRHRnVaR0Z5WkNCMFpYSnRjeUJoYm1RZ1kyOXVaR2wwYVc5dWN5QnZaaUIxYzJVc0lHTmxjblJwWm1sallYUmxJSEJ2YkdsamVTQmhibVFnWTJWeWRHbG1hV05oZEdsdmJpQndjbUZqZEdsalpTQnpkR0YwWlcxbGJuUnpMakEyQmdnckJnRUZCUWNDQVJZcWFIUjBjRG92TDNkM2R5NWhjSEJzWlM1amIyMHZZMlZ5ZEdsbWFXTmhkR1ZoZFhSb2IzSnBkSGt2TUE0R0ExVWREd0VCL3dRRUF3SUhnREFRQmdvcWhraUc5Mk5rQmdzQkJBSUZBREFOQmdrcWhraUc5dzBCQVFVRkFBT0NBUUVBRGFZYjB5NDk0MXNyQjI1Q2xtelQ2SXhETUlKZjRGelJqYjY5RDcwYS9DV1MyNHlGdzRCWjMrUGkxeTRGRkt3TjI3YTQvdncxTG56THJSZHJqbjhmNUhlNXNXZVZ0Qk5lcGhtR2R2aGFJSlhuWTR3UGMvem83Y1lmcnBuNFpVaGNvT0FvT3NBUU55MjVvQVE1SDNPNXlBWDk4dDUvR2lvcWJpc0IvS0FnWE5ucmZTZW1NL2oxbU9DK1JOdXhUR2Y4YmdwUHllSUdxTktYODZlT2ExR2lXb1IxWmRFV0JHTGp3Vi8xQ0tuUGFObVNBTW5CakxQNGpRQmt1bGhnd0h5dmozWEthYmxiS3RZZGFHNllRdlZNcHpjWm04dzdISG9aUS9PamJiOUlZQVlNTnBJcjdONFl0UkhhTFNQUWp2eWdhWndYRzU2QWV6bEhSVEJoTDhjVHFBPT0iOwoJInB1cmNoYXNlLWluZm8iID0gImV3b0pJbTl5YVdkcGJtRnNMWEIxY21Ob1lYTmxMV1JoZEdVdGNITjBJaUE5SUNJeU1ERTBMVEV4TFRFNElEQXdPalU0T2pVMklFRnRaWEpwWTJFdlRHOXpYMEZ1WjJWc1pYTWlPd29KSW5WdWFYRjFaUzFwWkdWdWRHbG1hV1Z5SWlBOUlDSTNaVEEyTUdObE5XRTJObUpqTkdKbU9UUTRORFF5T1RjeE5EWmpObUZpTVdJMllXTTRZVEl4SWpzS0NTSnZjbWxuYVc1aGJDMTBjbUZ1YzJGamRHbHZiaTFwWkNJZ1BTQWlNVEF3TURBd01ERXpNakF5T1RNek55STdDZ2tpWW5aeWN5SWdQU0FpTUM0eUlqc0tDU0owY21GdWMyRmpkR2x2YmkxcFpDSWdQU0FpTVRBd01EQXdNREkxTWpJMU5UQTBOU0k3Q2draWNYVmhiblJwZEhraUlEMGdJakVpT3dvSkltOXlhV2RwYm1Gc0xYQjFjbU5vWVhObExXUmhkR1V0YlhNaUlEMGdJakUwTVRZek1ERXhNell3TURBaU93b0pJblZ1YVhGMVpTMTJaVzVrYjNJdGFXUmxiblJwWm1sbGNpSWdQU0FpTmpoRk9UZEdOakF0UWpNME9DMDBSVEUyTFVJNU16VXROVEpDTVRZNFJURXhSakJESWpzS0NTSndjbTlrZFdOMExXbGtJaUE5SUNKMmFXSmxkMmx5WlY5MWJteHZZMnRoY0hBaU93b0pJbWwwWlcwdGFXUWlJRDBnSWprME1qZzVOREExTVNJN0Nna2lZbWxrSWlBOUlDSmpiMjB1WTI5dGJXOXVVbUZ3YVdSemIyWjBMblpwWW1WM2FYSmxJanNLQ1NKd2RYSmphR0Z6WlMxa1lYUmxMVzF6SWlBOUlDSXhORGM1TnpFeU5EVTVOREF3SWpzS0NTSndkWEpqYUdGelpTMWtZWFJsSWlBOUlDSXlNREUyTFRFeExUSXhJREEzT2pFME9qRTVJRVYwWXk5SFRWUWlPd29KSW5CMWNtTm9ZWE5sTFdSaGRHVXRjSE4wSWlBOUlDSXlNREUyTFRFeExUSXdJREl6T2pFME9qRTVJRUZ0WlhKcFkyRXZURzl6WDBGdVoyVnNaWE1pT3dvSkltOXlhV2RwYm1Gc0xYQjFjbU5vWVhObExXUmhkR1VpSUQwZ0lqSXdNVFF0TVRFdE1UZ2dNRGc2TlRnNk5UWWdSWFJqTDBkTlZDSTdDbjA9IjsKCSJlbnZpcm9ubWVudCIgPSAiU2FuZGJveCI7CgkicG9kIiA9ICIxMDAiOwoJInNpZ25pbmctc3RhdHVzIiA9ICIwIjsKfQ==";
//		byte byteArray[] = Base64.decodeBase64(str.getBytes());
//		
//		
//		JSONObject jsnobject = new JSONObject(new String(byteArray));
//		
//		System.out.println(" abhi "+ jsnobject);
//		
//		Gson gson = new Gson();
//		String jsonInString = gson.toJson(jsnobject);
//		
////		System.out.println(" jsonInString \t"+jsonInString.length());
//		System.out.println(" jsonInString \t"+jsonInString);
////		System.out.println(" jsonInString \t"+jsonInString.substring(8, jsonInString.length()-2));
////		
////		String result =jsonInString.substring(8, jsonInString.length()-2);
////		System.out.println(" result \t"+ result );
////		
////		String[] stringArray=jsnobject.toString().split(",");
////		
////		System.out.println(" 0 \t"+stringArray[0]);
////		System.out.println(" 1 \t"+stringArray[1]);
////		System.out.println(" 2 \t"+stringArray[2]);
////		System.out.println(" 3 \t"+stringArray[3]);
////		System.out.println(" 4 \t"+stringArray[4]);
////		
////		String [] a=result.split(",");
////		
////	System.out.println(a[0]);
////	System.out.println(a[1]);
////	System.out.println(a[2]);
////	System.out.println(a[3]);
////	System.out.println(a[4]);
	IOSPaymentVerifyRecepitUtil t= new IOSPaymentVerifyRecepitUtil();
	AppleReceiptVerifyResponse responseVO=t.verifyAppleInAppPurchaseRecepit(str, "https://sandbox.itunes.apple.com/verifyReceipt");
	System.out.println("**************");
	System.out.println("**************");
	System.out.println("**************");
	System.out.println("**************");
	System.out.println("**************");
	System.out.println(responseVO.getStatus());
	
	
	calendar.setTimeInMillis(Long.parseLong(responseVO.getReceipt().getOriginal_purchase_date_ms()));
	System.out.println(calendar.getTime());
	System.out.println(responseVO.getReceipt().getOriginal_purchase_date_ms());
	System.out.println("**************");
	System.out.println("**************");
	System.out.println("**************");
	System.out.println("**************");
	System.out.println("**************");
	}
	
	//Status : This tells about receipt verification status, which provides many codes based upon status of receipt verification.
	// 0 = Receipt verification is successful.
	// 21002 = Invalid receipt
	// 21006 = Which tells that provided receipt has expired.
	// 21007 = Which tells that we are verifying development receipt at production enviornment.
	// latest_receipt_info:  which provided list of receipts for all products purhcase from app store using same account. Now we fetching last receipt which is receipt of most recent product. This receipt contains all information of receipt like transactionId, receipt expiry, date and time of purchase etc.
	public	static AppleReceiptVerifyResponse verifyAppleInAppPurchaseRecepit(String receiptData, String VERIFICATION_URL){
		 AppleReceiptVerifyResponse responseVO=null;
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpPost request = new HttpPost(VERIFICATION_URL);
            JSONObject requestData = new JSONObject();
            requestData.put("receipt-data", receiptData);
            requestData.put("password", "4aa4887197cb4c47ae8b224b14ee29d1");
            StringEntity requestEntity = new StringEntity(requestData.toString());
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.setEntity(requestEntity);
            HttpResponse response = httpClient.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            JSONObject responseJSON = new JSONObject(responseBody);
            System.out.println(" responseJSON \t"+responseJSON);
             responseVO=fromJson(responseJSON.toString());
            System.err.println("responseVO\t"+responseVO);
            }
        catch (Exception ex) {
           System.out.println("Error occured due to ::"+ex.getMessage());
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
		return responseVO;
}
	
	public static AppleReceiptVerifyResponse fromJson(String json) throws JsonParseException,JsonMappingException, IOException{
		AppleReceiptVerifyResponse responseVO = new ObjectMapper().readValue(json, AppleReceiptVerifyResponse.class);
		System.out.println("Java Object created from JSON String ");
		System.out.println("JSON String : " + json);
		System.out.println("Java Object : " + responseVO);
		return responseVO;
		}


    }


package xyz.luan.dextra.parking.ws;

import io.yawp.commons.http.HttpException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public abstract class HttpFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(HttpFilter.class.getCanonicalName());

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (req.getMethod().equals("OPTIONS")) {
			resp.setStatus(200);
			resp.setHeader("Access-Control-Allow-Credentials", "true");
			resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
			resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD");
			resp.setHeader("Access-Control-Allow-Origin", "*");
			write(resp, "Ok!");
		}

		try {
			filter(req, resp);
			chain.doFilter(req, resp);
		} catch (HttpException ex) {
			handleException(resp, ex);
		} catch (Exception e) {
			LOGGER.log(SEVERE, "Unexpected error on Filter", e);
			handleException(resp, new HttpException(500, "Unexpected error on filter"));
		}
	}

	private void handleException(HttpServletResponse response, HttpException ex) {
		response.setStatus(ex.getHttpStatus());
		write(response, ex.getText());
	}

	private void write(HttpServletResponse response, String text) {
		try (PrintWriter writer = response.getWriter()) {
			writer.write(text);
		} catch (IOException e) {
			LOGGER.log(SEVERE, "Unexpected exception while writing response", e);
		}
	}

	protected abstract void filter(HttpServletRequest request, HttpServletResponse response) throws Exception;

	@Override
	public void destroy() {
	}
}

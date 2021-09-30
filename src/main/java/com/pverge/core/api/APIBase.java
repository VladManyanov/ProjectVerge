package com.pverge.core.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Default server API path. Game requests starts from "/v2" prefix.
 * Server must have GZIP filter enabled.
 * @author Hypernucle
 */
@ApplicationPath("/pverge")
public class APIBase extends Application {
}
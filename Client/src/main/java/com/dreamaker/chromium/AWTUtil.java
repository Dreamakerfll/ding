/*
 * Copyright 2014 JootMir Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Support: http://www.cnblogs.com/johness
 */
package com.dreamaker.chromium;

import javax.swing.JFrame;

/**
 * AWT工具集
 * 
 * @author ShawRyan
 */
public final class AWTUtil {
    /**
     * 获取某个窗体句柄(在Windows平台下)
     * 
     * @param window
     *         需要获取句柄的窗体对象
     * @return 窗体应用句柄
     */
    public static native int getWindowHandleInWindows(JFrame window);
}
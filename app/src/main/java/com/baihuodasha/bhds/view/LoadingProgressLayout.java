package com.baihuodasha.bhds.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.baihuodasha.bhds.R;
import com.baihuodasha.bhds.utils.CommonUtils;
import com.baihuodasha.bhds.utils.ThreadPoolFactory;

/**
 * @author IMXU
 * @time 2017/8/1 10:44
 * @des ${TODO}
 * 邮箱：butterfly_xu@sina.com
 */

public abstract class LoadingProgressLayout extends FrameLayout {

    public static final int	STATE_NONE		= -1;			// 默认情况
    public static final int	STATE_LOADING	= 0;			// 正在请求网络
    public static final int	STATE_EMPTY		= 1;			// 空状态
    public static final int	STATE_ERROR		= 2;			// 错误状态
    public static final int	STATE_SUCCESS	= 3;			// 成功状态

    public int				mCurState		= STATE_NONE;
    private View            mLoadingView;
    private View			mErrorView;
    private View			mEmptyView;
    private View			mSuccessView;


    public LoadingProgressLayout(Context context) {
        super(context);
        initView();
    }

    public LoadingProgressLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LoadingProgressLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        // 加载页面
        mLoadingView = View.inflate(CommonUtils.getContext(), R.layout.pager_loading, null);
        this.addView(mLoadingView);

        // 错误页面
        mErrorView = View.inflate(CommonUtils.getContext(), R.layout.pager_error, null);
        mErrorView.findViewById(R.id.error_btn_retry).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {// 从先触发加载数据
                loadData();
            }
        });
        this.addView(mErrorView);

        //空页面
        mEmptyView = View.inflate(CommonUtils.getContext(), R.layout.pager_empty, null);
        this.addView(mEmptyView);

        refreshUI();// LoadingPager初始化的时候
    }

    public void loadData() {
        if (mCurState != STATE_SUCCESS && mCurState != STATE_LOADING) {
            mCurState = STATE_LOADING;
            refreshUI();// 2. 显示正在加载数据
            ThreadPoolFactory.getNormalPool().execute(new LoadDataTask());
        }
    }

    class LoadDataTask implements Runnable {
        @Override
        public void run() {
            // 异步加载数据
            LoadedResult tempState = initData();
            // 处理加载结果
            mCurState = tempState.getState();
            CommonUtils.postTaskSafely(new Runnable() {
                @Override
                public void run() { // 刷新ui
                    refreshUI();// 3. 正在加载数据执行完成的时候
                }
            });
        }
    }

    /**
     * @des 根据当前状态显示不同的view
     * @call 1. LoadingPager初始化的时候
     * @call 2.	显示正在加载数据
     * @call 3. 正在加载数据执行完成的时候
     */
    private void refreshUI() {
        // 控制loading视图显示隐藏
        mLoadingView.setVisibility((mCurState == STATE_LOADING) || (mCurState == STATE_NONE) ? View.VISIBLE : View.GONE);
        // 控制emptyView视图显示隐藏
        mEmptyView.setVisibility((mCurState == STATE_EMPTY) ? View.VISIBLE : View.GONE);
        // 控制errorView视图显示隐藏
        mErrorView.setVisibility((mCurState == STATE_ERROR) ? View.VISIBLE : View.GONE);
        if (mSuccessView == null && mCurState == STATE_SUCCESS) {// 创建成功视图
            mSuccessView = initSuccessView();
            this.addView(mSuccessView);
        }
        if (mSuccessView != null) {// 控制success视图显示隐藏
            mSuccessView.setVisibility((mCurState == STATE_SUCCESS) ? View.VISIBLE : View.GONE);
        }
    }


    /**
     * @des 返回成功视图
     * @call 正在加载数据完成之后,并且数据加载成功,我们必须告知具体的成功视图
     */
    public abstract View initSuccessView();

    /**
     * @des 正在加载数据
     * @call loadData()方法被调用的时候
     */
    public abstract LoadedResult initData();

    public enum LoadedResult {
        SUCCESS(STATE_SUCCESS), ERROR(STATE_ERROR), EMPTY(STATE_EMPTY);
        int	state;
        public int getState() {
            return state;
        }
        LoadedResult(int state) {
            this.state = state;
        }
    }
}

package frc.fridowpi.motors;

import static edu.wpi.first.units.Units.RotationsPerSecond;

import java.util.Collection;
import java.util.Optional;

import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.HardwareLimitSwitchConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.OpenLoopRampsConfigs;
import com.ctre.phoenix6.configs.SlotConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.ForwardLimitTypeValue;
import com.ctre.phoenix6.signals.ForwardLimitValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.ReverseLimitTypeValue;
import com.ctre.phoenix6.signals.ReverseLimitValue;

import edu.wpi.first.units.Angle;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Velocity;
import frc.fridowpi.module.IModule;
import frc.fridowpi.module.Module;
import frc.fridowpi.motors.utils.FeedForwardValues;
import frc.fridowpi.motors.utils.PidValues;

/**
 * FridoFalcon500v6
 */
public class FridoFalcon500v6 implements FridolinsMotor {
	TalonFX motorProxy;
	MotorOutputConfigs motorOutputConfigs = new MotorOutputConfigs();
	Module moduleProxy = new Module();
	Optional<Integer> pidSlotIdx = Optional.empty();
	TalonFXConfiguration config = new TalonFXConfiguration();
	Optional<FeedForwardValues> feedForwardValues;
	double pidF = 0;

	public FridoFalcon500v6(int deviceNumber) {
		motorProxy = new TalonFX(deviceNumber);
	}

	public TalonFX asTalonFX() {
		return motorProxy;
	}

	private void convertFromTalonFXFeedbackDevice(FridoFeedBackDevice device) {
		switch (device) {
			case kBuildin:
				break;
			default:
				throw new Error("Feedbackdevice not avaible: " + device);
		}
	}

	@Override
	public void configEncoder(FridoFeedBackDevice device, int countsPerRev) {
		// motorProxy.configSelectedFeedbackSensor(convertFromTalonFXFeedbackDevice(device));
		convertFromTalonFXFeedbackDevice(device);
	}

	@Override
	public void setEncoderDirection(boolean inverted) {
		motorProxy.getConfigurator().apply(
				new FeedbackConfigs().withSensorToMechanismRatio(inverted ? -1 : 1));
	}

	@Override
	public void setEncoderPosition(double position) {
		motorProxy.getConfigurator().apply(
				new FeedbackConfigs().withFeedbackRotorOffset(position));
	}

	@Override
	public double getEncoderTicks() {
		return motorProxy.getRotorPosition().getValueAsDouble();
	}

	@Override
	public double getEncoderVelocity() {
		return motorProxy.getRotorVelocity().getValueAsDouble();
	}

	private ForwardLimitTypeValue convertFromFridoLimitSwitchPolarity(LimitSwitchPolarity polarity) {
		switch (polarity) {
			case kNormallyClosed:
				return ForwardLimitTypeValue.NormallyClosed;
			case kNormallyOpen:
				return ForwardLimitTypeValue.NormallyOpen;
			case kDisabled:
			default:
				throw new Error("Not implemented: " + polarity);
		}
	}

	@Override
	public void enableForwardLimitSwitch(LimitSwitchPolarity polarity, boolean enable) {
		motorProxy.getConfigurator().apply(
				new HardwareLimitSwitchConfigs()
						.withForwardLimitType(
								polarity == LimitSwitchPolarity.kNormallyOpen ? ForwardLimitTypeValue.NormallyClosed
										: ForwardLimitTypeValue.NormallyOpen)
						.withForwardLimitEnable(enable));
	}

	@Override
	public void enableReverseLimitSwitch(LimitSwitchPolarity polarity, boolean enable) {
		if (polarity == LimitSwitchPolarity.kDisabled) {
			motorProxy.getConfigurator().apply(
					new HardwareLimitSwitchConfigs().withReverseLimitEnable(enable));
		} else {
			motorProxy.getConfigurator().apply(
					new HardwareLimitSwitchConfigs()
							.withReverseLimitType(
									polarity == LimitSwitchPolarity.kNormallyOpen ? ReverseLimitTypeValue.NormallyClosed
											: ReverseLimitTypeValue.NormallyOpen)
							.withReverseLimitEnable(enable));
		}
	}

	@Override
	public boolean isForwardLimitSwitchActive() {
		return motorProxy.getForwardLimit().getValue() == ForwardLimitValue.ClosedToGround;
	}

	@Override
	public boolean isReverseLimitSwitchActive() {
		return motorProxy.getReverseLimit().getValue() == ReverseLimitValue.ClosedToGround;
	}

	@Override
	public void setVelocity(double velocity) {
		motorProxy.setControl(new VelocityVoltage(velocity));
	}

	@Override
	public void setPosition(double position) {
		motorProxy.setControl(new PositionVoltage(position));
	}

	@Override
	public void setAccelerationLimit(double maxAccelerationRotationsPerSecond) {
		// TODO: may not work correctly
		config.MotionMagic.MotionMagicAcceleration = maxAccelerationRotationsPerSecond;
	}

	@Override
	public void setPidTarget(double value, PidType type) {
		switch (type) {
			case position:
				motorProxy.setControl(new PositionVoltage(value).withFeedForward(pidF));
				break;
			case velocity:
				motorProxy.setControl(new VelocityVoltage(value).withFeedForward(pidF)); // TODO: Just call setVelocity?
				break;
			default:
				throw new Error("Not implemented: " + type);
		}
	}

	@Override
	public void selectPidSlot(int slotIndex) {
		this.pidSlotIdx = Optional.of(slotIndex);
	}

	@Override
	public void set(double speed) {
		motorProxy.set(speed);
	}

	@Override
	public double get() {
		return motorProxy.get();
	}

	@Override
	public void setInverted(boolean isInverted) {
		motorProxy.setInverted(isInverted);
	}

	@Override
	public boolean getInverted() {
		return motorProxy.getInverted();
	}

	@Override
	public void disable() {
		motorProxy.disable();
	}

	@Override
	public void stopMotor() {
		motorProxy.stopMotor();
	}

	@Override
	public void configOpenLoopRamp(double rate) {
		motorProxy.getConfigurator().apply(new OpenLoopRampsConfigs().withVoltageOpenLoopRampPeriod(rate));
	}

	@Override
	public double getPidTarget() {
		throw new Error("Not implemented");
	}

	public Optional<Double> tolerance;

	public void setPID(PidValues pidValues, FeedForwardValues feedForwardValues) {

		var pid = new SlotConfigs().withKP(pidValues.kP).withKI(pidValues.kI).withKD(pidValues.kD)
				.withKS(feedForwardValues.kS).withKV(feedForwardValues.kV).withKA(feedForwardValues.kA);
		pidValues.kF.ifPresent(kF -> pidF = kF);

		pid.SlotNumber = pidSlotIdx.orElse(0);
		tolerance = pidValues.tolerance;
		this.feedForwardValues = Optional.of(feedForwardValues);

		motorProxy.getConfigurator().apply(pid);
	}

	@Override
	public void setPID(PidValues pidValues) {

		var pid = new SlotConfigs().withKP(pidValues.kP).withKI(pidValues.kI).withKD(pidValues.kD);

		pidValues.kF.ifPresent(kF -> pidF = kF);

		pid.SlotNumber = pidSlotIdx.orElse(0);
		tolerance = pidValues.tolerance;

		motorProxy.getConfigurator().apply(pid);
	}

	@Override
	public boolean pidAtTarget() {
		return Math.abs(motorProxy.getClosedLoopError().getValueAsDouble()) < tolerance.orElse(0.01);
	}

	private NeutralModeValue convertFromFridoIdleMode(IdleMode mode) {
		switch (mode) {
			case kBrake:
				return NeutralModeValue.Brake;
			case kCoast:
				return NeutralModeValue.Coast;
			default:
				return NeutralModeValue.Coast;
		}
	}

	@Override
	public void setIdleMode(IdleMode type) {
		motorOutputConfigs.NeutralMode = NeutralModeValue.Brake;
		motorProxy.getConfigurator().apply(
				new MotorOutputConfigs().withNeutralMode(convertFromFridoIdleMode(type)));
	}

	@Override
	public void follow(FridolinsMotor master, DirectionType direction) {
		if (master instanceof TalonFX) {
			motorProxy.setControl(
					new Follower(((TalonFX) master).getDeviceID(), direction == DirectionType.invertMaster));
		} else if (master instanceof FridoFalcon500v6) {
			motorProxy.setControl(new Follower(((FridoFalcon500v6) master).asTalonFX().getDeviceID(),
					direction == DirectionType.invertMaster));
		} else {
			throw new Error("Can only follow 'com.ctre.phoenix6.hardware.TalonFX' or FridoFalcon motors");
		}
	}

	@Override
	public void factoryDefault() {
	}

	@Override
	public Collection<IModule> getAllSubModules() {
		return moduleProxy.getAllSubModules();
	}

	@Override
	public Collection<IModule> getSubModules() {
		return moduleProxy.getSubModules();
	}

	@Override
	public void registerSubmodule(IModule... subModule) {
		moduleProxy.registerSubmodule(subModule);
	}

	private boolean initialized = false;

	@Override
	public void init() {
		initialized = true;

	}

	@Override
	public boolean isInitialized() {
		return initialized;
	}

	@Override
	public void runPid() {
		/* Software pid calculations here */
	}
}
